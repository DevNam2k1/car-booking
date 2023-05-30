package com.project.BookingCar.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.BookingCar.domain.dto.page.ServiceBookingMediaDTO;
import com.project.BookingCar.domain.dto.page.ServiceServicesDTO;
import com.project.BookingCar.mapper.CommonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceQuotationRequest {
    @JsonProperty("ticket_id")
    @NotNull
    private Long ticketId;

    private List<ServiceServicesDTO> services;

    @JsonProperty("expected_hand_over_date")
    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = CommonFormat.LOCAL_DATE_TIME_FORMAT)
    private LocalDateTime expectedHandOverDate;

    @JsonProperty("expected_hand_over_time")
    @NotNull
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = CommonFormat.LOCAL_TIME_FORMAT)
    private LocalTime expectedHandOverTime;

    @JsonProperty("total_price")
    @NotNull
    private Double totalPrice;

    @JsonProperty("removed_images")
    private List<ServiceBookingMediaDTO> removedImages;
}
