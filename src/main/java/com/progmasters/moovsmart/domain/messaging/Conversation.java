package com.progmasters.moovsmart.domain.messaging;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public abstract class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "conversation")
    private List<Message> messages;

    public Conversation() {
        this.messages = new ArrayList<>();
    }

    Conversation addMessage(Message message) {
        this.messages.add(message);
        return this;
    }


    public List<Message> getMessages() {
        return this.messages;
    }

    public Long getId() {
        return id;
    }
}
