package com.project.BookingCar.repository;

import com.project.BookingCar.domain.model.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuelRepository extends JpaRepository<Fuel, Long> {
    Optional<Fuel> findByName(String name);
}
