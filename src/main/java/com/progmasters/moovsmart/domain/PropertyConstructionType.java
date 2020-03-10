package com.progmasters.moovsmart.domain;

public enum PropertyConstructionType {

    BRICK("Tégla"),
    PANEL("Panel");

    private String displayName;

    PropertyConstructionType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
