package com.project.BookingCar.domain.enums;

public enum ServiceMediaImageType {
    RESULT("RESULT"),
    RECEIPT("RECEIPT"),
    FINISHED("FINISHED");

    private String value;

    ServiceMediaImageType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
