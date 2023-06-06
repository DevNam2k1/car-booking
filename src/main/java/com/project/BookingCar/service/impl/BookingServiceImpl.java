package com.project.BookingCar.service.impl;

import com.google.gson.Gson;
import com.project.BookingCar.domain.dto.PriceQuotationDTO;
import com.project.BookingCar.domain.dto.appointment.CarServicesDTO;
import com.project.BookingCar.domain.dto.appointment.CarStatuesDTO;
import com.project.BookingCar.domain.dto.appointment.CreateAppointmentDTO;
import com.project.BookingCar.domain.dto.page.*;
import com.project.BookingCar.domain.enums.*;
import com.project.BookingCar.domain.model.*;
import com.project.BookingCar.mapper.CommonMapper;
import com.project.BookingCar.repository.*;
import com.project.BookingCar.service.BaseService;
import com.project.BookingCar.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl extends BaseService implements BookingService {
    private final RequestTicketRepository requestTicketRepository;
    private final GarageRepository garageRepository;
    private final DriverCarRepository driverCarRepository;
    private final RequestServiceRepository requestServiceRepository;
    private final RequestCarStatusRepository requestCarStatusRepository;
    private final RequestBookingMediaRepository requestBookingMediaRepository;
    private final DriverRepository driverRepository;
    private final RequestTicketRepositoryCustom requestTicketRepositoryCustom;
    private final ServiceTicketRepository serviceTicketRepository;
    private final CommonMapper commonMapper;
    @Override
    public void createNewBookingForAppointment(CreateAppointmentDTO createAppointmentDTO) {
        RequestTicket requestTicket = new RequestTicket();
        Gson gson = new Gson();
        saveCarOfDriver(requestTicket,createAppointmentDTO.getDriverCarId());
        saveGarageOfAppointment(requestTicket,createAppointmentDTO.getGarageId());
        requestTicket.setType(createAppointmentDTO.getTicketType());
        requestTicket.setAddressMark(gson.toJson(createAppointmentDTO.getAddressMarkDTO()));
        requestTicket.setAppointmentDate(createAppointmentDTO.getAppointmentDate());
        requestTicket.setAppointmentTime(createAppointmentDTO.getAppointmentTime());
        requestTicket.setStatus(RequestTicketsStatus.NEW);
        requestTicket.setCreateUser(getUsername());
        requestTicketRepository.save(requestTicket);
        saveRequestCarStatus(requestTicket,createAppointmentDTO.getVehicleCondition());
        saveRequestBookingMedia(requestTicket,createAppointmentDTO.getImages());
        saveRequestServices(requestTicket,createAppointmentDTO.getServices());
    }

    @Override
    public void createUpgradeOfTheCar(String description) {
        if (description.length() > 500){
            throw new IllegalArgumentException("Description can't exceed 500 character!!");
        }
        Driver driver = driverRepository.findByUsername(getUsername()).orElseThrow(() -> new IllegalArgumentException("Driver is not exist!!"));
        RequestTicket requestTicket = new RequestTicket();
        requestTicket.setDriverRefId(driver.getId());
        requestTicket.setDriverPhone(driver.getPhone());
        requestTicket.setDriverName(driver.getName());
        requestTicket.setDriverAddress(driver.getAddress());
        requestTicket.setCreateUser(getUsername());
        requestTicket.setType(RequestTicketType.UPGRADE);
        requestTicket.setStatus(RequestTicketsStatus.NEW);
        requestTicket.setDescription(description);
        requestTicketRepository.save(requestTicket);
    }

    @Override
    public Page<AppointmentDriverPageDTO> getPagingOfAppointmentByStatus(AppointmentDriverStatus status, Integer pageNum, Integer pageSize) {
        int page = pageNum == 0 ? pageNum : pageNum - 1;
        Pageable pageable = PageRequest.of(page, pageSize);
        Long driverId = driverRepository.findByUsername(getUsername()).orElseThrow(() -> new IllegalArgumentException("")).getId();
        List<RequestTicket> appointments = new ArrayList<>();
        switch (status){
            case UPCOMING:
                appointments = requestTicketRepositoryCustom.listAppointmentScheduleForDriverWithRequestTicketStatus(
                        Arrays.asList(
                                RequestTicketsStatus.NEW,
                                RequestTicketsStatus.GARAGE_CONFIRMED,
                                RequestTicketsStatus.GARAGE_NO_ACTION,
                                RequestTicketsStatus.GARAGE_CANCELED
                        ),
                        Arrays.asList(
                                ServiceTicketsStatus.CHECKING,
                                ServiceTicketsStatus.CHECKED
                        ),
                        Collections.singletonList(
                                RequestTicketsStatus.CANCELED
                        ),
                        driverId,
                        pageable);
                break;
            case PENDING:
                appointments = requestTicketRepositoryCustom.listAppointmentScheduleForDriverWithServiceTicketStatus(
                        Collections.singletonList(ServiceTicketsStatus.WAITING_CUSTOMER_APPROVE_PRICE),
                        Collections.singletonList(
                                RequestTicketsStatus.CANCELED
                        ),
                        driverId,
                        pageable
                );
                break;
            case PROCESSING:
                appointments = requestTicketRepositoryCustom.listAppointmentScheduleForDriverWithServiceTicketStatus(
                        Arrays.asList(
                                ServiceTicketsStatus.CUSTOMER_APPROVED_PRICE,
                                ServiceTicketsStatus.FIXED,
                                ServiceTicketsStatus.PAYMENT_CONFIRMATION),
                        Collections.singletonList(
                                RequestTicketsStatus.CANCELED
                        ),
                        driverId,
                        pageable);
                break;
            case COMPLETED:
                appointments = requestTicketRepositoryCustom.listAppointmentScheduleForDriverWithServiceTicketStatus(
                        Arrays.asList(
                                ServiceTicketsStatus.GARAGE_HANDED_OVER_CAR,
                                ServiceTicketsStatus.COMPLETED
                        ),
                        Collections.singletonList(
                                RequestTicketsStatus.CANCELED
                        ),
                        driverId,
                        pageable);
                break;
            case CANCEL:
                appointments =  requestTicketRepositoryCustom.listAppointmentScheduleForDriverWithRequestTicketStatus(
                        Collections.singletonList(RequestTicketsStatus.CANCELED),
                        Collections.singletonList(
                                ServiceTicketsStatus.CANCELED
                        ),
                        Collections.emptyList(),
                        driverId,
                        pageable);
                break;
        }
        return new PageImpl<>(commonMapper.convertToResponseList(appointments, AppointmentDriverPageDTO.class), pageable, appointments.size());
    }

    @Override
    public Integer countRequestTicketOfWaitingCustomerApprove() {
        return serviceTicketRepository.countRequestTicketOfStatusWaitingCustomerApprove(getUserId(), ServiceTicketsStatus.WAITING_CUSTOMER_APPROVE_PRICE.getValue());
    }

    @Override
    public RequestTicketDTO getRequestTicketInformation(Long requestTicketId) {
        return commonMapper.convertToResponse(requestTicketRepository.findById(requestTicketId).orElseThrow(() -> new IllegalArgumentException("Request ticket is not exist !!!!")), RequestTicketDTO.class);
    }

    @Override
    public InspectionResultDTO getInspectionResultForCustomer(Long requestTicketId) {
        RequestTicket rt = requestTicketRepository.findById(requestTicketId).orElseThrow(() -> new IllegalArgumentException("Request ticket is not exist !!!!"));
        List<ServiceBookingMedia> serviceBookingMedia = rt.getServiceTickets().get(0).getServiceTicketServiceBookingMedias();
        return InspectionResultDTO
                .builder()
                .bookingMedias(commonMapper.convertToResponseList(serviceBookingMedia, ServiceBookingMediaDTO.class))
                .description(rt.getServiceTickets().get(0).getDescription())
                .build();
    }

    @Override
    public PriceQuotationDTO getDriverReceivePriceQuotation(Long requestTicketId) {
        RequestTicket requestTicket = requestTicketRepository.findById(requestTicketId).orElseThrow(() -> new IllegalArgumentException("Request ticket is not exist !!!!"));
        PriceQuotationDTO priceQuotationDTO = commonMapper.convertToResponse(requestTicket, PriceQuotationDTO.class);
        List<ServiceBookingMedia> serviceBookingMedias = new ArrayList<>();
        for (ServiceBookingMedia serviceBookingMedia : requestTicket.getServiceTickets().get(0).getServiceTicketServiceBookingMedias()){
            if (ServiceMediaImageType.RECEIPT.equals(serviceBookingMedia.getImageType())){
                serviceBookingMedias.add(serviceBookingMedia);
            }
        }
        priceQuotationDTO.setServices(commonMapper.convertToResponseList(requestTicket.getServiceTickets().get(0).getServiceTicketServiceServices(), ServiceServicesDTO.class));
        priceQuotationDTO.setBookingMediaDTOS(commonMapper.convertToResponseList(serviceBookingMedias, ServiceBookingMediaDTO.class));
        priceQuotationDTO.setTotalPrice(requestTicket.getServiceTickets().get(0).getTotalPrice());
        priceQuotationDTO.setExpectedHandOverTime(requestTicket.getServiceTickets().get(0).getExpectedHandOverTime());
        priceQuotationDTO.setExpectedHandOverDate(requestTicket.getServiceTickets().get(0).getExpectedHandOverDate());
        return priceQuotationDTO;
    }

    @Override
    public void approvePriceQuotation(Long requestTicketId) {
        RequestTicket requestTicket = requestTicketRepository.findById(requestTicketId).orElseThrow(() -> new IllegalArgumentException("Request ticket is not exist !!!!"));
        requestTicket.setPriceCheckedDate(LocalDateTime.now());
        requestTicket.setPriceCheckedUser(getUsername());
        requestTicketRepository.save(requestTicket);
        ServiceTicket serviceTicket = serviceTicketRepository.findByRequestTicket(requestTicket).orElseThrow(() -> new IllegalArgumentException("Service ticket is not exist !!!!"));
        serviceTicket.setStatus(ServiceTicketsStatus.CUSTOMER_APPROVED_PRICE);
        serviceTicket.setCustomerApprovedPriceDate(LocalDateTime.now());
        serviceTicket.setCustomerApprovedPriceUser(getUsername());
        serviceTicketRepository.save(serviceTicket);
    }

    @Override
    public void driverConfirmPayment(Long requestTicketId, PaymentType paymentType) {
        RequestTicket requestTicket = requestTicketRepository.findById(requestTicketId).orElseThrow(() -> new IllegalArgumentException("Request ticket is not exist !!!!"));
        ServiceTicket serviceTicket = serviceTicketRepository.findByRequestTicket(requestTicket).orElseThrow(() -> new IllegalArgumentException("Service ticket is not exist !!!!"));
        serviceTicket.setPaymentType(paymentType.getValue());
        serviceTicket.setIsPayment(false);
        serviceTicketRepository.save(serviceTicket);
    }

    @Override
    public List<ServiceBookingMediaDTO> getHandedOverCar(Long requestTicketId) {
        RequestTicket requestTicket = requestTicketRepository.findById(requestTicketId).orElseThrow(() -> new IllegalArgumentException("Request ticket is not exist !!!!"));
        List<ServiceBookingMediaDTO> serviceBookingMediaDTOS = new ArrayList<>();
        for(ServiceBookingMedia sbm : requestTicket.getServiceTickets().get(0).getServiceTicketServiceBookingMedias()) {
            if (ServiceMediaImageType.FINISHED.equals(sbm.getImageType())){
                ServiceBookingMediaDTO serviceBookingMediaDTO = new ServiceBookingMediaDTO(sbm.getId(), sbm.getImageUrl(), sbm.getImageType());
                serviceBookingMediaDTOS.add(serviceBookingMediaDTO);
            }
        }
        return serviceBookingMediaDTOS;
    }

    private void saveRequestServices(RequestTicket requestTicket,List<CarServicesDTO> services) {
        for (CarServicesDTO car:services) {
            RequestServices requestServices = new RequestServices();
            requestServices.setType("SERVICE");
            requestServices.setName(car.getName());
            requestServices.setRefId(car.getId());
            requestServices.setRequestTicket(requestTicket);
            requestServiceRepository.save(requestServices);
        }
    }

    private void saveRequestBookingMedia(RequestTicket requestTicket,List<String> images) {
        for (String image: images
             ) {
            RequestBookingMedia bookingMedia = new RequestBookingMedia();
            bookingMedia.setImageUrl(image);
            bookingMedia.setImageType(RequestMediaImageType.CAR_STATUS);
            bookingMedia.setRequestTicket(requestTicket);
            requestBookingMediaRepository.save(bookingMedia);
        }
    }

    private void saveRequestCarStatus(RequestTicket requestTicket,List<CarStatuesDTO> vehicleCondition) {
        for (CarStatuesDTO status: vehicleCondition
             ) {
            RequestCarStatus requestCarStatus = new RequestCarStatus();
            requestCarStatus.setCarStatusId(status.getId());
            requestCarStatus.setValue(status.getName());
            requestCarStatus.setRequestTicket(requestTicket);
            requestCarStatusRepository.save(requestCarStatus);
        }
    }

    private void saveGarageOfAppointment(RequestTicket requestTicket,Long garageId) {
        Garage garage = garageRepository.findById(garageId).orElseThrow(() -> new IllegalArgumentException("Garage is not exist!"));
        requestTicket.setGarageRefId(garage.getId());
        requestTicket.setGarageAddress(garage.getAddress());
        requestTicket.setGarageName(garage.getName());
        requestTicket.setGaragePhone("0293847362");
    }

    private void saveCarOfDriver(RequestTicket requestTicket,Long driverCarId) {
        DriverCar driverCar = driverCarRepository.findById(driverCarId).orElseThrow(() -> new IllegalArgumentException("Car of driver is not exist!"));
        requestTicket.setCarBrand(driverCar.getCar().getBrand().getName());
        requestTicket.setCarLine(driverCar.getCar().getModel());
        requestTicket.setCarRefId(driverCar.getCar().getId());
        requestTicket.setCarEngineNumber(driverCar.getEngineNumber());
        requestTicket.setCarChassisNumber(driverCar.getChassisNumber());
        requestTicket.setCarLicensePlate(driverCar.getLicensePlate());
        requestTicket.setDriverName(driverCar.getDriver().getName());
        requestTicket.setDriverPhone(driverCar.getDriver().getPhone());
        requestTicket.setDriverAddress(driverCar.getDriver().getAddress());
        requestTicket.setDriverRefId(driverCar.getDriver().getId());
    }
}
