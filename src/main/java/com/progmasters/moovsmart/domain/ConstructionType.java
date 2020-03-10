package com.progmasters.moovsmart.domain;

public enum ConstructionType {

    BRICK("Tégla"),
    PANEL("Panel");

    private String displayName;

    ConstructionType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
