package com.project.BookingCar.repository;

import com.project.BookingCar.domain.model.Car;
import com.project.BookingCar.domain.param.CarParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CarRepositoryCustom {
    Page<Car> getPagingOfCar(CarParam carParam, Pageable pageable);
}
