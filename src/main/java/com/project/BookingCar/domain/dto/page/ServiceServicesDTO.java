package com.project.BookingCar.domain.dto.page;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.BookingCar.mapper.CommonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceServicesDTO {

    @JsonProperty("ref_id")
    @NotNull
    private Long refId;

    @JsonProperty("name")
    @NotNull
    private String name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("price")
    @NotNull
    private Double price;

    @JsonProperty("warranty_time")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = CommonFormat.LOCAL_DATE_TIME_FORMAT)
    private LocalDateTime warrantyTime;

    @JsonProperty("details")
    private String details;
}
