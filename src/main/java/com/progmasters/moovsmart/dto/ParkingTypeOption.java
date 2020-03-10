package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.ParkingType;

public class ParkingTypeOption {

    private String name;
    private String displayName;

    public ParkingTypeOption(ParkingType parkingType) {
        this.name = parkingType.toString();
        this.displayName = parkingType.getDisplayName();
    }

    public ParkingTypeOption() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
