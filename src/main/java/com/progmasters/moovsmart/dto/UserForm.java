package com.progmasters.moovsmart.dto;

public class UserForm {
    private String email;
    private String passwordEncoded;
    private PersonalDetailsForm userDetails;

    public UserForm() {
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordEncoded() {
        return passwordEncoded;
    }

    public PersonalDetailsForm getPersonalDetails() {
        return userDetails;
    }
}
