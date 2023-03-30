package com.project.BookingCar.domain.dto.page;

import com.project.BookingCar.domain.dto.BodyStyleDTO;
import com.project.BookingCar.domain.dto.BrandDTO;
import com.project.BookingCar.domain.dto.FuelDTO;
import com.project.BookingCar.domain.model.BodyStyle;
import com.project.BookingCar.domain.model.Brand;
import com.project.BookingCar.domain.model.Fuel;
import lombok.Data;

@Data
public class CarPageDTO {

    private Long id;
    private String model;
    private String origin;
    private BrandDTO brand;
    private BodyStyleDTO bodyStyle;
    private FuelDTO fuel;
}
