package com.project.BookingCar.repository;

import com.project.BookingCar.domain.model.ServiceBookingMedia;
import com.project.BookingCar.domain.model.ServiceTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceMediaBookingRepository extends JpaRepository<ServiceBookingMedia, Long> {
    void deleteAllByServiceTicket(ServiceTicket serviceTicket);
}
