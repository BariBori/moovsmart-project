package com.progmasters.moovsmart.domain;

public enum ParkingType {

    YARD("Udvari beálló"),
    GARAGE("Garázs"),
    STREET("Utca, közterület");

    private String displayName;

    ParkingType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
