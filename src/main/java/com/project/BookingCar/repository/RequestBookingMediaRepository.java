package com.project.BookingCar.repository;

import com.project.BookingCar.domain.model.RequestBookingMedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestBookingMediaRepository extends JpaRepository<RequestBookingMedia, Long> {
}
