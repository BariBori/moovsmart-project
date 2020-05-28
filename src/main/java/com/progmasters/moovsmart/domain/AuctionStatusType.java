package com.progmasters.moovsmart.domain;

public enum AuctionStatusType {

    INACTIVE("Inactive"),
    ACTIVE("Active");

    private String displayName;

    public String getDisplayName() {
        return displayName;
    }

    AuctionStatusType(String displayName) {
        this.displayName = displayName;
    }
}

