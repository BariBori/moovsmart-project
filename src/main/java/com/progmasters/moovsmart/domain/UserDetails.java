package com.progmasters.moovsmart.domain;

import javax.persistence.*;

@Entity
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @OneToOne
    @JoinColumn(name = "userDetails")
    private User user;

    public UserDetails(String fullName) {
        this.fullName = fullName;
    }

    public UserDetails() {
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }
}
