package com.project.BookingCar.service;

import com.project.BookingCar.domain.dto.GarageDTO;
import com.project.BookingCar.domain.dto.page.GaragePageDTO;
import com.project.BookingCar.domain.param.GarageParam;
import org.springframework.data.domain.Page;

public interface GarageService {
    Page<GaragePageDTO> getPagingOfGarage(GarageParam garageParam, Integer pageNo, Integer pageSize);
    Page<GaragePageDTO> getPagingOfGarageBetweenLatAndLong(Integer instance, Double latTitude, Double longTitude, Integer pageNo, Integer pageSize);
    GaragePageDTO getGarageById(Long id);
    void updateGarage(Long id, GarageDTO garageDTO);
    void deleteGarage(Long id);

}
