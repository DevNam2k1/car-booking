package com.project.BookingCar.domain.dto.security;

import com.project.BookingCar.domain.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {
    @Size(max = 20)
    private String username;

    @Email
    private String email;

    @Size(min = 6, max = 20)
    private String password;

    private Set<String> role;
}
