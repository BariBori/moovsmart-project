package com.progmasters.moovsmart.domain;

import javax.persistence.*;

@Entity
public class PersonalDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @OneToOne
    @JoinColumn(name = "userDetails")
    private User user;

    public PersonalDetails(String fullName) {
        this.fullName = fullName;
    }

    public PersonalDetails() {
    }

    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }
}
