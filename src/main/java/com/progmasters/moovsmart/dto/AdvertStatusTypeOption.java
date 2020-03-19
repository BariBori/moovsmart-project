package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.AdvertStatusType;

public class AdvertStatusTypeOption {

    private String name;
    private String displayName;

    public AdvertStatusTypeOption(){}

    public AdvertStatusTypeOption(AdvertStatusType advertStatusType) {
        this.name = advertStatusType.toString();
        this.displayName = advertStatusType.getDisplayName();
    }

    public AdvertStatusTypeOption() {
    }

    public AdvertStatusTypeOption(String displayName) {
        this.displayName = displayName;
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
