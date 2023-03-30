package com.project.BookingCar.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public abstract class BaseService {

    public String getUsername(){
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        return user.getUsername();
    }
}
