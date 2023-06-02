package com.project.BookingCar.domain.enums;

public enum PaymentType {
    CASH("CASH"),
    BANKING("BANKING");

    private String value;

    PaymentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
