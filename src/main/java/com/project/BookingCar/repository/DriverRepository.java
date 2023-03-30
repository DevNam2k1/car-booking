package com.project.BookingCar.repository;

import com.project.BookingCar.domain.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Long> {
}
