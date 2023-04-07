package com.project.BookingCar.repository;

import com.project.BookingCar.domain.enums.RequestTicketsStatus;
import com.project.BookingCar.domain.enums.ServiceTicketsStatus;
import com.project.BookingCar.domain.model.RequestTicket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RequestTicketRepositoryCustom {
    List<RequestTicket> listAppointmentScheduleForDriverWithRequestTicketStatus(
            List<RequestTicketsStatus> statusesRequestTicket,
            List<ServiceTicketsStatus> serviceTicketsStatuses,
            List<RequestTicketsStatus> notInStatusesRequestTicket,
            Long driverId,
            Pageable pageable);
}
