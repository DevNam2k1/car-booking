package com.project.BookingCar.service;

import com.project.BookingCar.domain.dto.CarDTO;
import com.project.BookingCar.domain.dto.page.CarPageDTO;
import com.project.BookingCar.domain.param.CarParam;
import org.springframework.data.domain.Page;

public interface CarService {
    Page<CarDTO> getPagingOfCar(CarParam carParam, Integer pageNumber, Integer pageSize);

    CarPageDTO getCarById(Long id);

    void createCar(CarDTO carDTO);

    void updateCar(Long id, CarDTO carDTO);

    void deleteCar(Long id);
}
