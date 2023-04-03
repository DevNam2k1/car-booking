package com.project.BookingCar.domain.dto.appointment;

import lombok.Data;

@Data
public class AddressMarkDTO {
    private Double latTitude;

    private Double longTitude;

    private String address;
}
