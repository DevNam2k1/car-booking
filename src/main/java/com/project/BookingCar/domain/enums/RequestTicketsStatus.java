package com.project.BookingCar.domain.enums;

public enum RequestTicketsStatus {
    NEW("NEW"),

    NEW_WITH_BEST_PRICE("NEW_WITH_BEST_PRICE"),
    CUSTOMER_CHECKED_IN("CUSTOMER_CHECKED_IN"),
    PROCESSING("PROCESSING"),
    GARAGE_CONFIRMED("GARAGE_CONFIRMED"),
    GARAGE_NO_ACTION("GARAGE_NO_ACTION"),
    COMPLETED("COMPLETED"),
    CANCELED("CANCELED"),
    GARAGE_CANCELED("GARAGE_CANCELED");

    private String value;

    RequestTicketsStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
