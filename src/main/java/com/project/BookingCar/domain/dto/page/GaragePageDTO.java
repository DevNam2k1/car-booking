package com.project.BookingCar.domain.dto.page;

import com.project.BookingCar.domain.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GaragePageDTO {
    private Long id;
    private String name;
    private String username;
    private String address;
    private Float latiTude;
    private Float longiTude;
    private String description;
}
