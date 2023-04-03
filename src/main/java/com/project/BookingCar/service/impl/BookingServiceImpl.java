package com.project.BookingCar.service.impl;

import com.google.gson.Gson;
import com.project.BookingCar.domain.dto.appointment.CarServicesDTO;
import com.project.BookingCar.domain.dto.appointment.CarStatuesDTO;
import com.project.BookingCar.domain.dto.appointment.CreateAppointmentDTO;
import com.project.BookingCar.domain.enums.RequestMediaImageType;
import com.project.BookingCar.domain.enums.RequestTicketsStatus;
import com.project.BookingCar.domain.model.*;
import com.project.BookingCar.repository.*;
import com.project.BookingCar.service.BaseService;
import com.project.BookingCar.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
