package com.project.BookingCar.domain.dto.page;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InspectionResultDTO {

    private String description;

    private List<ServiceBookingMediaDTO> bookingMedias;
}
