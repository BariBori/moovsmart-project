package com.progmasters.moovsmart.domain.messaging;

import com.progmasters.moovsmart.domain.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User sender;

    @ManyToOne
    @JoinColumn
    private Conversation conversation;

    private LocalDateTime sentAt;

    private String text;

    public Message() {
    }

    public Message(User sender, Conversation conversation, String message) {
        this.sender = sender;
        this.conversation = conversation;
        this.text = message;
        this.sentAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public User getSender() {
        return sender;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public String getText() {
        return text;
    }
}
