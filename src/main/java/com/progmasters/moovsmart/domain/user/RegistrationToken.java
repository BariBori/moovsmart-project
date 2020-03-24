package com.progmasters.moovsmart.domain.user;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
public class RegistrationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    @Column(unique = true)
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID uuid;
    private Instant issuedAt;

    public static RegistrationToken forUser(User user) {
        RegistrationToken result = new RegistrationToken();
        result.uuid = UUID.randomUUID();
        result.issuedAt = Instant.now();
        result.user = user;
        return result;
    }

    public Long getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public User getUser() {
        return user;
    }

    public Instant getIssuedAt() {
        return issuedAt;
    }
}
