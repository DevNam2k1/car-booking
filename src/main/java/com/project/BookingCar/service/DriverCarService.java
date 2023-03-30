package com.project.BookingCar.service;

import com.project.BookingCar.domain.dto.DriverCarDTO;
import com.project.BookingCar.domain.dto.page.DriverCarPageDTO;
import com.project.BookingCar.domain.model.DriverCar;
import com.project.BookingCar.domain.param.DriverCarParam;
import org.springframework.data.domain.Page;

public interface DriverCarService {
    Page<DriverCarPageDTO> getPagingCarOfDriverId(Long driverId, Integer pageNum, Integer pageSize);

    Page<DriverCarPageDTO> getPagingCarAndSearchingForParam(DriverCarParam driverCarParam, Integer pageNum, Integer pageSize);
    DriverCarPageDTO getCarOfDriverById( Long driverCarId);
    void createCarOfDriver(DriverCarDTO driverCarDTO);

    void updateCarOfDriver(Long id, DriverCarDTO driverCarDTO);

    void disableCarOfDriver(Long id);

}
