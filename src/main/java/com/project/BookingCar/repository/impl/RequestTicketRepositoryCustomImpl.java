package com.project.BookingCar.repository.impl;

import com.project.BookingCar.domain.enums.RequestTicketsStatus;
import com.project.BookingCar.domain.enums.ServiceTicketsStatus;
import com.project.BookingCar.domain.model.RequestTicket;
import com.project.BookingCar.repository.RequestTicketRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RequestTicketRepositoryCustomImpl implements RequestTicketRepositoryCustom {
    @Override
    public List<RequestTicket> listAppointmentScheduleForDriverWithRequestTicketStatus(List<RequestTicketsStatus> statusesRequestTicket, List<ServiceTicketsStatus> serviceTicketsStatuses, List<RequestTicketsStatus> notInStatusesRequestTicket, Long driverId, Pageable pageable) {

        return null;
    }
}
