package com.project.BookingCar.service.impl;

import com.project.BookingCar.domain.dto.GarageDTO;
import com.project.BookingCar.domain.dto.page.GaragePageDTO;
import com.project.BookingCar.domain.enums.RequestTicketsStatus;
import com.project.BookingCar.domain.enums.SuperStatus;
import com.project.BookingCar.domain.model.Garage;
import com.project.BookingCar.domain.model.RequestTicket;
import com.project.BookingCar.domain.model.ServiceTicket;
import com.project.BookingCar.domain.param.GarageParam;
import com.project.BookingCar.mapper.CommonMapper;
import com.project.BookingCar.repository.*;
import com.project.BookingCar.service.BaseService;
import com.project.BookingCar.service.GarageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GarageServiceImpl extends BaseService implements GarageService {
    private final ServiceTicketRepository serviceTicketRepository;

    private final GarageRepository garageRepository;

    private final UserRepository userRepository;

    private final GarageRepositoryCustom garageRepositoryCustom;

    private final CommonMapper mapper;
    private final RequestTicketRepository requestTicketRepository;


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
        RequestTicket rt = requestTicketRepository.findById(requestTicket).orElseThrow(() -> new IllegalArgumentException("Request ticket not exits"));
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
        RequestTicket rt = requestTicketRepository.findById(requestTicketId).orElseThrow(() -> new IllegalArgumentException("Request ticket not exits"));
        log.info("Request ticket status : {}",rt.getStatus());
        if (RequestTicketsStatus.COMPLETED.equals(rt.getStatus())){
        rt.setStatus(RequestTicketsStatus.COMPLETED);
        ServiceTicket serviceTicket = ServiceTicket
                .builder()
                .requestTicket(rt)
                .build();
        serviceTicket.setCreateUser(getUsername());
        serviceTicketRepository.save(serviceTicket);
        requestTicketRepository.save(rt);
        } else {
            throw new IllegalArgumentException("Driver have confirmed at garage ^^");
        }
    }
}
