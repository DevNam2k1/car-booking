package com.project.BookingCar.repository;

import com.project.BookingCar.domain.model.RequestCarStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestCarStatusRepository extends JpaRepository<RequestCarStatus, Long> {
}
