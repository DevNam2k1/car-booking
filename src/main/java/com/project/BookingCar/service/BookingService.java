package com.project.BookingCar.service;

import com.project.BookingCar.domain.dto.appointment.CreateAppointmentDTO;

public interface BookingService {
    void createNewBookingForAppointment(CreateAppointmentDTO createAppointmentDTO);
}
