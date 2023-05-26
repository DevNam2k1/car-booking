package com.project.BookingCar.domain.dto.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestServicesDTO {
    private Long id;

    private Long refId;

    private String name;

    private String type;

    private Double price;

    private Long parentId;
}
