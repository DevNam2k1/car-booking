package com.project.BookingCar.domain.enums;

public enum SuperStatus {
    DRAFT("DRAFT"), ACCEPTED("ACCEPTED"), REJECTED("REJECTED");

    private String value;

    SuperStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
