package com.project.BookingCar.schedule;

import com.project.BookingCar.domain.enums.RequestTicketsStatus;
import com.project.BookingCar.domain.model.RequestTicket;
import com.project.BookingCar.repository.RequestTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class RequestTicketCronjob {
    private final RequestTicketRepository requestTicketRepository;

    //Cronjob after 2 minute if request ticket by NEW , change status by GARAGE_NO_ACTION
    @Scheduled(cron = "0 */2 * * * *")
    private void updateRequestTicketWhenGarageNoAction(){
        List<RequestTicket> requestTickets = requestTicketRepository.findAll();
        for (RequestTicket rt: requestTickets) {
            if (RequestTicketsStatus.NEW.equals(rt.getStatus())){
                rt.setStatus(RequestTicketsStatus.GARAGE_NO_ACTION);
                requestTicketRepository.save(rt);
            }
        }
    }
}
