package com.progmasters.moovsmart.domain.messaging;

import com.progmasters.moovsmart.domain.PropertyAdvert;
import com.progmasters.moovsmart.domain.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private PropertyAdvert advert;
    @OneToOne
    private User advertiser;
    @OneToOne
    private User enquirer;

    @OneToMany(mappedBy = "topic")
    private List<Message> messages;

    public Topic() {
    }

    public Topic(PropertyAdvert advert, User enquirer) {
        this.advert = advert;
        this.advertiser = advert.getUser();
        this.enquirer = enquirer;
        this.messages = new ArrayList<>();

    }

    public Topic addMessage(Message message) {
        this.messages.add(message);
        return this;
    }

    public Long getId() {
        return id;
    }

    public PropertyAdvert getAdvert() {
        return advert;
    }

    public User getAdvertiser() {
        return advertiser;
    }

    public User getEnquirer() {
        return enquirer;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
