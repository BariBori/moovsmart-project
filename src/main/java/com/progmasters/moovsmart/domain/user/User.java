package com.progmasters.moovsmart.domain.user;

import com.progmasters.moovsmart.domain.PropertyAdvert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
public class User {

    private static final String EMAIL_REGEXP =
            "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Pattern(regexp = EMAIL_REGEXP)
    private String email;

    @Column(unique = true)
    @Size(min = 3)
    private String userName;

    @NotBlank
    private String passwordHash;

    @Column
    @OneToMany(mappedBy = "user")
    private List<PropertyAdvert> propertyAdvert;

    @NotNull
    @ElementCollection(targetClass = UserRole.class)
    @Enumerated(EnumType.STRING)
    private List<UserRole> userRoles;

    @NotNull
    @OneToMany
    private List<PropertyAdvert> savedAdverts = new ArrayList<>();

    @NotNull
    private Boolean activated;

    public User(String email, String userName, String passwordHash, UserRole... userRoles) {
        this.email = email;
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.activated = false;
        this.userRoles = new ArrayList<>();
        this.userRoles.addAll(Arrays.asList(userRoles));
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public List<PropertyAdvert> getSavedAdverts() {
        return savedAdverts;
    }

    public User addSavedAdvert(PropertyAdvert advert) {
        this.savedAdverts.add(advert);
        return this;
    }

    public User removeSavedAdvert(PropertyAdvert advert) {
        this.savedAdverts.removeIf(ad -> ad.getId() == advert.getId());
        return this;
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
