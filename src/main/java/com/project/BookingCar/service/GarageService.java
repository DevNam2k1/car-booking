package com.project.BookingCar.service;

import com.project.BookingCar.domain.dto.GarageDTO;
import com.project.BookingCar.domain.param.GarageParam;
import org.springframework.data.domain.Page;

public interface GarageService {
    Page<GarageDTO> getPagingOfGarage(GarageParam garageParam, Integer pageNo, Integer pageSize);

    GarageDTO getGarageById(Long id);
    void createNewGarage(GarageDTO garageDTO);

    void updateGarage(Long id, GarageDTO garageDTO);

    void deleteGarage(Long id);

}
