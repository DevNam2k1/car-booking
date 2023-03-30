package com.project.BookingCar.service;

import com.project.BookingCar.domain.dto.message.MessageResponse;
import com.project.BookingCar.domain.dto.security.JwtResponse;
import com.project.BookingCar.domain.dto.security.LoginRequest;
import com.project.BookingCar.domain.dto.security.SignupRequest;

public interface AuthService {
    JwtResponse signIn(LoginRequest loginRequest);

    MessageResponse signUp(SignupRequest signUpRequest);
}
