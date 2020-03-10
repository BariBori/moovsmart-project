package com.progmasters.moovsmart.dto;

public class UserForm {
    private String email;
    private String password;
    private PersonalDetailsForm personalDetails;

    public UserForm() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public PersonalDetailsForm getPersonalDetails() {
        return personalDetails;
    }
}
