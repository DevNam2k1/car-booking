package com.project.BookingCar.service;

import com.project.BookingCar.domain.dto.FuelDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FuelService {

    Page<FuelDTO> getPagingFuel(String name,Integer pageSize,Integer pageNo);

    void createNewFuel(FuelDTO fuelDTO);

    void updateFuel(Long id,FuelDTO fuelDTO);

    void deleteFuel(Long id);
}
