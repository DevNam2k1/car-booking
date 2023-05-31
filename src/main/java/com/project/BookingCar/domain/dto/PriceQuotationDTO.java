package com.project.BookingCar.domain.dto;


import com.project.BookingCar.domain.dto.page.ServiceBookingMediaDTO;
import com.project.BookingCar.domain.dto.page.ServiceServicesDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceQuotationDTO {

    private List<ServiceServicesDTO> services;

    private LocalDateTime expectedHandOverDate;

    private LocalTime expectedHandOverTime;

    private Double totalPrice;

    private List<ServiceBookingMediaDTO> bookingMediaDTOS;

}
