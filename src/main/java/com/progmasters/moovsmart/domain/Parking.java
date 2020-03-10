package com.progmasters.moovsmart.domain;

public enum Parking {

    YARD("Udvari beálló"),
    GARAGE("Garázs"),
    STREET("Utca, közterület");

    private String displayName;

    Parking(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
