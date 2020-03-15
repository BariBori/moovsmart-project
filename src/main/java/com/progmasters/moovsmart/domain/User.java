package com.progmasters.moovsmart.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String email;
    private String passwordHash;
    @OneToOne(mappedBy = "user")
    private PersonalDetails personalDetails;
    @ElementCollection(targetClass = UserRole.class)
    @Enumerated(EnumType.STRING)
    private List<UserRole> userRoles;
    private Boolean activated;

    public User(String email, String passwordHash, PersonalDetails personalDetails, UserRole... userRoles) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.personalDetails = personalDetails;
        this.activated = false;
        this.userRoles = new ArrayList<>();
        this.userRoles.addAll(Arrays.asList(userRoles));
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

    public List<UserRole> getUserRoles() {
        return userRoles;
    }

    public Boolean isActivated() {
        return activated;
    }

    public void confirmRegistration() {
        activated = true;
    }
}
