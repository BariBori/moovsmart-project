package com.progmasters.moovsmart.domain.messaging;

import com.progmasters.moovsmart.domain.User;

import javax.persistence.*;
import java.time.Instant;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User sender;

    @ManyToOne
    private Topic topic;

    private Instant sentAt;

    public Long getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public Topic getTopic() {
        return topic;
    }

    public Instant getSentAt() {
        return sentAt;
    }
}
