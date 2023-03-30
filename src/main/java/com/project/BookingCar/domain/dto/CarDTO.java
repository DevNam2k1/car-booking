package com.project.BookingCar.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {

    private String model;

    private String origin;

    private Long brandId;

    private Long bodyStyleId;

    private Long fuelId;
}
