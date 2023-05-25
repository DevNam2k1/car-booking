package com.project.BookingCar.repository;


import com.project.BookingCar.domain.model.RequestTicket;
import com.project.BookingCar.domain.model.ServiceTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ServiceTicketRepository extends JpaRepository<ServiceTicket, Long> {

    @Query(value =  "SELECT COUNT(rt.id) FROM car_booking.request_ticket rt " +
                    "INNER JOIN car_booking.service_ticket st " +
                    "ON rt.id = st.request_ticket_id " +
                    "WHERE rt.driver_ref_id = :driverId AND st.status = :status " +
                    "GROUP BY rt.id", nativeQuery = true)
    Integer countRequestTicketOfStatusWaitingCustomerApprove(Long driverId, String status);

    Optional<ServiceTicket> findByRequestTicket(RequestTicket requestTicket);
}
