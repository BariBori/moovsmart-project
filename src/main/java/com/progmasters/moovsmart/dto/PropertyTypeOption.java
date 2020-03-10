package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.PropertyType;

public class PropertyTypeOption {

    private String name;
    private String displayName;

    public PropertyTypeOption(PropertyType propertyType) {
        this.name = propertyType.toString();
        this.displayName = propertyType.getDisplayName();
    }

    public PropertyTypeOption() {
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
