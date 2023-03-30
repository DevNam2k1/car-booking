package com.project.BookingCar.repository;

import com.project.BookingCar.domain.model.DriverCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverCarRepository extends JpaRepository<DriverCar, Long> {
}
