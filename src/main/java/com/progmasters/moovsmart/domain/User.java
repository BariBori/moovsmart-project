package com.progmasters.moovsmart.domain;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String passwordHash;
    @OneToOne(mappedBy = "user")
    private PersonalDetails personalDetails;

    private Boolean activated;

    public User(String email, String passwordHash, PersonalDetails personalDetails) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.personalDetails = personalDetails;
        this.activated = false;
    }

    public User() {

    }

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Boolean isActivated() {
        return activated;
    }

    public void confirmRegistration() {
        activated = true;
    }
}
