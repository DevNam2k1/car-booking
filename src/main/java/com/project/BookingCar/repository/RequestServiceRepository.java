package com.project.BookingCar.repository;

import com.project.BookingCar.domain.model.RequestServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestServiceRepository extends JpaRepository<RequestServices,Long> {
}
