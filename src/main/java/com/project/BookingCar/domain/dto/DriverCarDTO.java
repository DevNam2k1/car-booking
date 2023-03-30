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
public class DriverCarDTO {
    private String chassisNumber;
    private String engineNumber;
    private String licensePlate;
    private String color;
    private String carImage;
    private String status;
    private Long driverId;
    private Long carId;
}
