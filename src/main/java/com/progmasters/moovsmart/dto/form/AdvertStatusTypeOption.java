package com.progmasters.moovsmart.dto.form;

import com.progmasters.moovsmart.domain.AdvertStatusType;

public class AdvertStatusTypeOption {

    private String name;
    private String displayName;

    public AdvertStatusTypeOption(){}

    public AdvertStatusTypeOption(AdvertStatusType advertStatusType) {
        this.name = advertStatusType.toString();
        this.displayName = advertStatusType.getDisplayName();
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
