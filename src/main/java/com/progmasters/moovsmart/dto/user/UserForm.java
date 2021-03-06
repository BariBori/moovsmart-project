package com.progmasters.moovsmart.dto.user;

import com.progmasters.moovsmart.dto.form.PersonalDetailsForm;

public class UserForm {
    private String email;
    private String password;
    private String userName;
    private PersonalDetailsForm personalDetails;

    public UserForm() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public PersonalDetailsForm getPersonalDetails() {
        return personalDetails;
    }


}
