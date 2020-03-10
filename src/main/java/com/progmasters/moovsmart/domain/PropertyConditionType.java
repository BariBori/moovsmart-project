package com.progmasters.moovsmart.domain;

public enum PropertyConditionType {

    NEW("Újépítésű"),
    RENEWED("Felújított"),
    GOODCONDITION("Jó állapotú"),
    MODERATE("Közepes állapotú"),
    TOBERENOVATED("Felújítandó"),
    UNFINISHED("Befejezetlen");

    private String displayName;

    PropertyConditionType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
