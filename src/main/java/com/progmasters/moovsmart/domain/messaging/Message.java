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
    @JoinColumn
    private Topic topic;

    private Instant sentAt;

    private String text;

    public Message() {
    }

    public Message(User sender, String text) {
        this.sender = sender;
        this.sentAt = Instant.now();
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Long getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public Instant getSentAt() {
        return sentAt;
    }
}
