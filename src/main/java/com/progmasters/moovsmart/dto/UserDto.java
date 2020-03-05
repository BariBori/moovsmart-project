package com.progmasters.moovsmart.dto;

public class UserDto {
    private String email;
    private String passwordEncoded;
    private PersonalDetailsDto userDetails;

    public UserDto() {
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordEncoded() {
        return passwordEncoded;
    }

    public PersonalDetailsDto getPersonalDetails() {
        return userDetails;
    }
}
