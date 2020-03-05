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
    private UserDetails userDetails;

    public User(String email, String passwordHash, UserDetails userDetails) {
        this.email = email;
        this.passwordHash = passwordHash;
        this.userDetails = userDetails;
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
