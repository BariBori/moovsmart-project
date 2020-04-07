package com.progmasters.moovsmart.dto.form;

import com.progmasters.moovsmart.domain.PropertyConditionType;

public class PropertyConditionOption {

    private String name;
    private String displayName;

    public PropertyConditionOption(PropertyConditionType propertyConditionType) {
        this.name = propertyConditionType.toString();
        this.displayName = propertyConditionType.getDisplayName();
    }

    public PropertyConditionOption() {
    }

    public PropertyConditionOption(String displayName) {
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
