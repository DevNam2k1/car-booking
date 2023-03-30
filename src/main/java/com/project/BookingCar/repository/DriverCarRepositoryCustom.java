package com.project.BookingCar.repository;

import com.project.BookingCar.domain.model.DriverCar;
import com.project.BookingCar.domain.param.DriverCarParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DriverCarRepositoryCustom {
    Page<DriverCar> getCarOfDriverByDriverId(Long driverId, Pageable pageable);

    Page<DriverCar> getAllCarOfDriverAndSearchingForParam(DriverCarParam driverCarParam, Pageable pageable);
}
