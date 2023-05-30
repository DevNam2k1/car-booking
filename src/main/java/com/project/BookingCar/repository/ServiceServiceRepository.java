package com.project.BookingCar.repository;

import com.project.BookingCar.domain.model.ServiceServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceServiceRepository extends JpaRepository<ServiceServices, Long> {
}
