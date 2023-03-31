package com.project.BookingCar.domain.dto.page;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpertPageDTO {
    private Long id;
    private String username;
    private String phone;
    private String address;
    private String status;

}
