package com.project.BookingCar.domain.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class GarageDTO {
    private Long id;
    private String name;
    private String address;
    private Float latiTude;
    private Float longiTude;
    private String description;
}
