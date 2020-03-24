package com.progmasters.moovsmart.domain.messaging;

import com.progmasters.moovsmart.domain.PropertyAdvert;
import com.progmasters.moovsmart.domain.User;

import javax.persistence.*;
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
