package com.project.BookingCar.domain.enums;

public enum RoleEnum {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_MODERATOR("ROLE_MODERATOR");


    private String value;

    RoleEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
