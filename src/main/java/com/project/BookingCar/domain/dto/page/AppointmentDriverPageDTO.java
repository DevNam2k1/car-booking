package com.project.BookingCar.domain.dto.page;

import com.project.BookingCar.domain.enums.RequestTicketType;
import com.project.BookingCar.domain.enums.RequestTicketsStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
public class AppointmentDriverPageDTO {
    private Long id;
    private RequestTicketsStatus status;
    private RequestTicketType type;
    private String licensePlate;
    private String garageName;
    private LocalDateTime appointmentTime;
}
