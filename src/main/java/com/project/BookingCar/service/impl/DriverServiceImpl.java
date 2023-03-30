package com.project.BookingCar.service.impl;

import com.project.BookingCar.domain.dto.DriverDTO;
import com.project.BookingCar.domain.model.Driver;
import com.project.BookingCar.repository.DriverRepository;
import com.project.BookingCar.service.BaseService;
import com.project.BookingCar.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl extends BaseService implements DriverService {

    private final DriverRepository driverRepository;

    @Override
    public void updateDriver(Long id, DriverDTO driverDTO) {
        Driver driver = driverRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Driver is not exist"));
        driver.setUpdatedAt(LocalDateTime.now());
        driver.setUsername(getUsername());
        driverRepository.save(driver);
    }

    @Override
    public void disableOrActiveDriver(Long id) {
        Driver driver = driverRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Driver is not exist"));
        driver.setUpdatedAt(LocalDateTime.now());
        driver.setUpdateUser(getUsername());
        if (driver.getStatus() == "disable") {
            driver.setStatus("active");
        } else {
            driver.setStatus("disable");
        }
        driverRepository.save(driver);
    }
}
