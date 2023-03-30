package com.project.BookingCar.domain.param;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GarageParam {
    private String name;
    private String username;
    private String address;

    private Float latiTude;

    private Float longiTude;
}
