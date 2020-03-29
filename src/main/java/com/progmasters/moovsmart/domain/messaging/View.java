package com.progmasters.moovsmart.domain.messaging;

import com.progmasters.moovsmart.domain.user.User;

import javax.persistence.*;

@Entity
public abstract class View<T extends Conversation> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    @OneToOne
    protected T conversation;

    public View() {
    }

    public View(User user, T conversation) {
        this.user = user;
        this.conversation = conversation;
    }

    public User getUser() {
        return user;
    }

    public abstract T getConversation();

    public Long getId() {
        return id;
    }
}
