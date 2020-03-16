package com.progmasters.moovsmart.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    @Column(unique = true)
    @Pattern(regexp = EMAIL_REGEX)
    private String email;
    private String passwordHash;
    @OneToOne(mappedBy = "user")
    private PersonalDetails personalDetails;
    @NotEmpty
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
