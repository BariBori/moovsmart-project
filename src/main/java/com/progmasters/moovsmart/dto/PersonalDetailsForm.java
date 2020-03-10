package com.progmasters.moovsmart.dto;

public class PersonalDetailsForm {
    private String fullName;

    public String getFullName() {
        return fullName;
    }

    public PersonalDetailsForm(String fullName) {
        this.fullName = fullName;
    }

    public PersonalDetailsForm() {
    }
}
