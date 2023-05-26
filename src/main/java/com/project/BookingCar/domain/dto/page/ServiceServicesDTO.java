package com.project.BookingCar.domain.dto.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceServicesDTO {

    private Long id;

    private Long refId;

    private String name;

    private String type;

    private Double price;

    private LocalDateTime warrantyTime;

    private String details;
}
