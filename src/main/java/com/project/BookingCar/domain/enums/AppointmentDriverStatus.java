package com.project.BookingCar.domain.enums;

public enum AppointmentDriverStatus {
    UPCOMING("UPCOMING"),
    PENDING("PENDING"),
    PROCESSING("PROCESSING"),
    COMPLETED("COMPLETED"),
    CANCEL("CANCEL");

    private String value;

    AppointmentDriverStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
