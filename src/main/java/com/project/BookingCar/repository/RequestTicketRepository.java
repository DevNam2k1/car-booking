package com.project.BookingCar.repository;

import com.project.BookingCar.domain.model.RequestTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestTicketRepository extends JpaRepository<RequestTicket, Long> {
}
