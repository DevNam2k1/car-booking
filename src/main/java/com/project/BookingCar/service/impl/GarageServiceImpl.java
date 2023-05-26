package com.project.BookingCar.service.impl;

import com.project.BookingCar.domain.dto.GarageDTO;
import com.project.BookingCar.domain.dto.page.GaragePageDTO;
import com.project.BookingCar.domain.enums.*;
import com.project.BookingCar.domain.model.Garage;
import com.project.BookingCar.domain.model.RequestTicket;
import com.project.BookingCar.domain.model.ServiceBookingMedia;
import com.project.BookingCar.domain.model.ServiceTicket;
import com.project.BookingCar.domain.param.GarageParam;
import com.project.BookingCar.mapper.CommonMapper;
import com.project.BookingCar.repository.*;
import com.project.BookingCar.service.BaseService;
import com.project.BookingCar.service.FileStorageService;
import com.project.BookingCar.service.GarageService;
import com.project.BookingCar.util.CheckNumberOfImageUtils;
import com.project.BookingCar.util.DescriptionCheckerUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class GarageServiceImpl extends BaseService implements GarageService {
    private final ServiceTicketRepository serviceTicketRepository;

    private final GarageRepository garageRepository;

    private final UserRepository userRepository;

    private final GarageRepositoryCustom garageRepositoryCustom;

    private final ServiceMediaBookingRepository mediaBookingRepository;

    private final CommonMapper mapper;
    private final RequestTicketRepository requestTicketRepository;

    private final FileStorageService fileStorageService;


    @Override
    public Page<GaragePageDTO> getPagingOfGarage(GarageParam garageParam, Integer pageNo, Integer pageSize) {
        int page = pageNo == 0? pageNo : pageNo - 1;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Garage> garagePage = garageRepositoryCustom.getPagingGarage(garageParam, pageable);
        return mapper.convertToResponsePage(garagePage,GaragePageDTO.class,pageable);
    }

    @Override
    public Page<GaragePageDTO> getPagingOfGarageBetweenLatAndLong(Integer distance, Double latTitude, Double longTitude, Integer pageNo, Integer pageSize) {
        int page = pageNo == 0? pageNo : pageNo - 1;
        Pageable pageable = PageRequest.of(page,pageSize);
        Double newLat = (double) (latTitude + (distance*2)/111.12);
        Double newLong = (double) (longTitude + (distance*2)/111.12);
        List<Garage> garages = garageRepository.findAll();
        List<Garage> garageList = new ArrayList<>();
        for (Garage g: garages) {
            if (latTitude <= g.getLatiTude() && g.getLatiTude() <= newLat && longTitude <= g.getLongiTude() && g.getLongiTude() <= newLong){
                garageList.add(g);
            }
        }
        return new PageImpl<>(mapper.convertToResponseList(garageList, GaragePageDTO.class), pageable, garageList.size());
    }

    @Override
    public GaragePageDTO getGarageById(Long id) {
        Garage garage = garageRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Garage not exist")
                );
        return mapper.convertToResponse(garage, GaragePageDTO.class);
    }


    @Override
    public void updateGarage(Long id, GarageDTO garageDTO) {
        Garage garage = garageRepository.findById(id)
                .orElseThrow(
                        () -> new IllegalArgumentException("Garage not exist")
                );
        garage.setUpdatedAt(LocalDateTime.now());
        garage.setAddress(garageDTO.getAddress());
        garage.setDescription(garageDTO.getDescription());
        garage.setLatiTude(garageDTO.getLatiTude());
        garage.setLongiTude(garageDTO.getLongiTude());
        garage.setName(garageDTO.getName());
        garage.setUpdateUser(getUsername());
        garageRepository.save(garage);
    }

    @Override
    public void deleteGarage(Long id) {
        garageRepository.deleteById(id);
    }

    @Override
    public void handleIncomingRequest(Long requestTicket, SuperStatus status) {
        // Garage accepted appointment of car
        RequestTicket rt = getRequestTicket(requestTicket);
        if (SuperStatus.ACCEPTED.equals(status)) {
            rt.setStatus(RequestTicketsStatus.GARAGE_CONFIRMED);
        } else {
            log.info("Request ticket have cancel!!!");
        }
        requestTicketRepository.save(rt);
    }

    @Override
    public void confirmCheckIn(Long requestTicketId) {
        // Garage confirm driver check in
        RequestTicket rt = getRequestTicket(requestTicketId);
        log.info("Request ticket status : {}",rt.getStatus());
        if (!RequestTicketsStatus.COMPLETED.equals(rt.getStatus())){
        rt.setStatus(RequestTicketsStatus.COMPLETED);
        ServiceTicket serviceTicket = ServiceTicket
                .builder()
                .requestTicket(rt)
                .status(ServiceTicketsStatus.CHECKING)
                .build();
        serviceTicket.setCreateUser(getUsername());
        serviceTicketRepository.save(serviceTicket);
        requestTicketRepository.save(rt);
        } else {
            throw new IllegalArgumentException("Driver have confirmed at garage ^^");
        }
    }

    @Override
    public void inspectionResult(Long requestTicketId, List<MultipartFile> resultImages, String description, CRUDEnums feature) {
        CheckNumberOfImageUtils.check(resultImages);
        DescriptionCheckerUtils.isDescriptionUnder500Words(description);
        // Garage inspection result for driver
        ServiceTicket st = serviceTicketRepository.findByRequestTicket(getRequestTicket(requestTicketId)).orElseThrow(() -> new IllegalArgumentException("Service ticket not exist!!!"));
        if (ServiceTicketsStatus.CHECKED.equals(st.getStatus()) && CRUDEnums.UPDATE.equals(feature)) {
            mediaBookingRepository.deleteAllByServiceTicket(st);
            saveServiceTicketAndMediaBooking(st,resultImages,description);
        } else if(ServiceTicketsStatus.CHECKING.equals(st.getStatus()) && CRUDEnums.CREATE.equals(feature)) {
            saveServiceTicketAndMediaBooking(st,resultImages,description);
        } else {
            throw new IllegalArgumentException("Car has inspection result !!!!!");
        }
    }

    private RequestTicket getRequestTicket(Long requestTicketId){
        return requestTicketRepository.findById(requestTicketId).orElseThrow(() -> new IllegalArgumentException("Request ticket not exist!!"));
    }

    private void saveServiceTicketAndMediaBooking(ServiceTicket st ,List<MultipartFile> resultImages, String description){

        st.setDescription(description);
        st.setStatus(ServiceTicketsStatus.CHECKED);
        st.setCheckedDate(LocalDateTime.now());
        st.setCheckedUser(getUsername());


        for (MultipartFile multipartFile : resultImages) {
            ServiceBookingMedia serviceBookingMedia = new ServiceBookingMedia();
            String fileName = fileStorageService.storeFile(multipartFile);
            serviceBookingMedia.setImageType(ServiceMediaImageType.RESULT);
            log.info("Filename: {}", multipartFile.getOriginalFilename());
            serviceBookingMedia.setImageUrl(fileName);
            serviceBookingMedia.setServiceTicket(st);
            mediaBookingRepository.save(serviceBookingMedia);
        }

        serviceTicketRepository.save(st);
    }
}
