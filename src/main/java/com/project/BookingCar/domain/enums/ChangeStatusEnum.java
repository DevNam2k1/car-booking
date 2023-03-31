package com.project.BookingCar.domain.enums;

public enum ChangeStatusEnum {
    DISABLE("disable"),
    ACTIVE("active");
    private String value;

    ChangeStatusEnum(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
