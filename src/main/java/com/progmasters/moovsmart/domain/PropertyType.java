package com.progmasters.moovsmart.domain;

public enum PropertyType {

    FLAT("Lakás"),
    HOUSE("Ház"),
    SITE("Telek"),
    GARAGE("Garázs"),
    SUMMERHOUSE("Nyaraló"),
    OFFICE("Iroda"),
    PREMISES("Üzlethelyiség");

    public final String displayName;

    PropertyType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
