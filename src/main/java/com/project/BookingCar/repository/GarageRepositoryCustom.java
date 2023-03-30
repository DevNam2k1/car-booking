package com.project.BookingCar.repository;

import com.project.BookingCar.domain.model.Garage;
import com.project.BookingCar.domain.param.GarageParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GarageRepositoryCustom {
    Page<Garage> getPagingGarage(GarageParam garageParam, Pageable pageable);
}
