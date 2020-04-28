package com.progmasters.moovsmart.domain.messaging;

import com.progmasters.moovsmart.domain.user.User;

import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id", "conversation_id" }) })
public abstract class View<T extends Conversation> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    @OneToOne
    @Type(type = "com.progmasters.moovsmart.domain.messaging.Chat")
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
