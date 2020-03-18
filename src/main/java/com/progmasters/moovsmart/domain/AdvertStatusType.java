package com.progmasters.moovsmart.domain;

public enum AdvertStatusType {

    FORAPPROVAL("Jóváhagyásra vár"),
    ACTIVE("Aktív"),
    ARCHIVE("Archív"),
    BANNED("Tiltott");

    private String displayName;

    AdvertStatusType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }



}
