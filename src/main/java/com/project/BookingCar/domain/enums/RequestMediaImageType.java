package com.project.BookingCar.domain.enums;

public enum RequestMediaImageType {
    CAR_STATUS("CAR_STATUS");

    private String value;

    RequestMediaImageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}