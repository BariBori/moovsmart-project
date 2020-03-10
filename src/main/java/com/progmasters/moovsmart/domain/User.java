package com.progmasters.moovsmart.domain;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String passwordHash;

    public PersonalDetails getPersonalDetails() {
        return personalDetails;
    }

    @OneToOne(mappedBy = "user")
    private PersonalDetails personalDetails;

    public User(String email, String passwordHash, PersonalDetails personalDetails) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.personalDetails = personalDetails;
    }

    public User() {

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
}
