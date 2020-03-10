package com.progmasters.moovsmart.domain;

public enum PropertyCondition {

    NEW("Újépítésű"),
    RENEWED("Felújított"),
    GOODCONDITION("Jó állapotú"),
    MODERATE("Közepes állapotú"),
    TOBERENOVATED("Felújítandó"),
    UNFINISHED("Befejezetlen");

    private String displayName;

    PropertyCondition(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
