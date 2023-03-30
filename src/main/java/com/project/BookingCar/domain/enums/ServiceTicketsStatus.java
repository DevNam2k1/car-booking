package com.project.BookingCar.domain.enums;

public enum ServiceTicketsStatus {
    CHECKED("CHECKED"),
    CHECKING("CHECKING"),
    WAITING_CUSTOMER_APPROVE_PRICE("WAITING_CUSTOMER_APPROVE_PRICE"),
    CUSTOMER_APPROVED_PRICE("CUSTOMER_APPROVED_PRICE"),
    FIXED("FIXED"),
    PAYMENT_CONFIRMATION("PAYMENT_CONFIRMATION"),
    COMPLETED("COMPLETED"),
    GARAGE_HANDED_OVER_CAR("GARAGE_HANDED_OVER_CAR"),
    CANCELED("CANCELED");

    private String value;

    ServiceTicketsStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}