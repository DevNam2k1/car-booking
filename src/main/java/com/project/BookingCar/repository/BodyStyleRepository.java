package com.project.BookingCar.repository;

import com.project.BookingCar.domain.model.BodyStyle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BodyStyleRepository extends JpaRepository<BodyStyle, Long> {
    Optional<BodyStyle> findByName(String name);
}
