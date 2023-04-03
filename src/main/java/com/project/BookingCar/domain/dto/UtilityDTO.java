package com.project.BookingCar.domain.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class UtilityDTO {
    private Long id;
    private String name;
    private String code;
}
