package com.project.BookingCar.domain.enums;

public enum RequestTicketType {
    REPAIR("REPAIR"),
    MAINTENANCE("MAINTENANCE"),
    UPGRADE("UPGRADE"),
    PAINT("PAINT"),
    SPA("SPA");

    private String value;

    RequestTicketType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
