package com.project.BookingCar.domain.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverCarParam {
    private String chassisNumber;
    private String engineNumber;
    private String licensePlate;
    private String color;
}
