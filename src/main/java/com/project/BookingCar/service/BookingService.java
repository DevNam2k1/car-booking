package com.project.BookingCar.service;

import com.project.BookingCar.domain.dto.appointment.CreateAppointmentDTO;
import com.project.BookingCar.domain.dto.page.AppointmentDriverPageDTO;
import com.project.BookingCar.domain.enums.AppointmentDriverStatus;
import org.springframework.data.domain.Page;

public interface BookingService {
    void createNewBookingForAppointment(CreateAppointmentDTO createAppointmentDTO);

    void createUpgradeOfTheCar(String description);

    Page<AppointmentDriverPageDTO> getPagingOfAppointmentByStatus(AppointmentDriverStatus status, Integer pageNum, Integer pageSize);
}
