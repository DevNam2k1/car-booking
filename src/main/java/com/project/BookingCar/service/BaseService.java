package com.project.BookingCar.service;

import com.project.BookingCar.domain.model.User;
import com.project.BookingCar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


public abstract class BaseService {

    @Autowired
    private UserRepository userRepository;

    public String getUsername(){
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return user.getUsername();
    }

    public Long getUserId(){
        User user = userRepository.findByUsername(getUsername()).orElseThrow(() -> new IllegalArgumentException("User not exist!!!"));
        return user.getId();
    }
}
