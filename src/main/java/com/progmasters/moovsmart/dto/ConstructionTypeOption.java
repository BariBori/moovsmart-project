package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.PropertyConstructionType;

public class ConstructionTypeOption {

    private String name;
    private String displayName;

    public ConstructionTypeOption(PropertyConstructionType propertyConstructionType) {
        this.name = propertyConstructionType.toString();
        this.displayName = propertyConstructionType.getDisplayName();
    }

    public ConstructionTypeOption() {
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
