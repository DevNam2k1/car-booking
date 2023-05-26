package com.project.BookingCar.domain.enums;

public enum CRUDEnums {
    CREATE("CREATE"),
    UPDATE("UPDATE"),
    READ("READ"),
    DELETE("DELETE");
    private String value;

    CRUDEnums(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
