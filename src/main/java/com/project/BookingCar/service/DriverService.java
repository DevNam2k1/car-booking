package com.project.BookingCar.service;

import com.project.BookingCar.domain.dto.DriverDTO;

public interface DriverService {
    void updateDriver(Long id,DriverDTO driverDTO);

    void disableOrActiveDriver(Long id);
}
