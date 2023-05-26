package com.project.BookingCar.domain.dto.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestSurveyDTO {
    private Long id;

    private Long refId;

    private String name;

    private Double minPrice;

    private Double maxPrice;
}
