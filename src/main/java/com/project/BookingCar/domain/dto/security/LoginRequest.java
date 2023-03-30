package com.project.BookingCar.domain.dto.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
    @Size(max = 20)
    private String username;

    @Size(min = 6, max = 20)
    private String password;
}
