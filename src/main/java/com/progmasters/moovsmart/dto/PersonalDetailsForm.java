package com.progmasters.moovsmart.dto;

public class PersonalDetailsForm {
    private String fullName;

    public PersonalDetailsForm(String fullName) {
        this.fullName = fullName;
    }

    public PersonalDetailsForm() {
    }

    public String getFullName() {
        return fullName;
    }
}
