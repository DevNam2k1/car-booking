package com.project.BookingCar.repository;

import com.project.BookingCar.domain.model.Garage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GarageRepository extends JpaRepository<Garage, Long> {
}
