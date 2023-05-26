package com.project.BookingCar.domain.dto.page;

import com.project.BookingCar.domain.enums.RequestMediaImageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestBookingMediaDTO {

    private Long id;

    private String imageUrl;

    private RequestMediaImageType imageType;
}
