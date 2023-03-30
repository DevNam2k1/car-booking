package com.project.BookingCar.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO {
    private String phone;
    private String gender;
    private String status;
    private String address;
}
