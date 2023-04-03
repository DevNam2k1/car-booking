package com.project.BookingCar.domain.dto.appointment;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.BookingCar.domain.enums.RequestTicketType;
import com.project.BookingCar.domain.enums.RequestTicketsStatus;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CreateAppointmentDTO {
    @NotNull
    private Long driverCarId;
    @NotNull
    private Long garageId;
    @NotNull
    private RequestTicketType ticketType;
    @NotNull
    private RequestTicketsStatus status;
    private String carKm;
    @NotNull
    private List<CarStatuesDTO> vehicleCondition;
    private List<CarServicesDTO> services;
    private List<String> images;
    private LocalDateTime appointmentDate;
    private LocalDateTime appointmentTime;
    private AddressMarkDTO addressMarkDTO;
    private String description;
}
