package com.project.BookingCar.domain.dto.page;

import com.project.BookingCar.domain.dto.CarDTO;
import com.project.BookingCar.domain.dto.DriverDTO;
import com.project.BookingCar.domain.model.Car;
import com.project.BookingCar.domain.model.Driver;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class DriverCarPageDTO {

    private Long id;
    private String chassisNumber;
    private String engineNumber;
    private String licensePlate;
    private String color;
    private String carImage;
    private String status;
    private DriverDTO driver;
    private CarDTO car;
}
