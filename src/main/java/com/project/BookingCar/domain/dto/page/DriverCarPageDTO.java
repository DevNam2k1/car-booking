package com.project.BookingCar.domain.dto.page;

import com.project.BookingCar.domain.model.Car;
import com.project.BookingCar.domain.model.Driver;

import javax.persistence.*;

public class DriverCarPageDTO {

    private Long id;
    private String chassisNumber;
    private String engineNumber;
    private String licensePlate;
    private String color;
    private String carImage;
    private String status;
    private Driver driver;
    private Car car;
}
