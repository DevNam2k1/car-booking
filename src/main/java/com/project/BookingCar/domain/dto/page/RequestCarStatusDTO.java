package com.project.BookingCar.domain.dto.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCarStatusDTO {

    private Long id;

    private Long carStatusId;

    private String value;
}
