package com.progmasters.moovsmart.domain;

import com.progmasters.moovsmart.domain.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private PropertyAdvert propertyAdvertId;

    @ManyToOne
    private User userId;

    @Column
    private LocalDateTime dateTimeOfBid;

    @Column
    private Integer amountOfBid;

    public Bid() {
    }

    public Bid(PropertyAdvert propertyAdvertId, User userId, Integer amountOfBid) {
        this.propertyAdvertId = propertyAdvertId;
        this.userId = userId;
        this.dateTimeOfBid = LocalDateTime.now().plusDays(1);
        this.amountOfBid = amountOfBid;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PropertyAdvert getPropertyAdvertId() {
        return propertyAdvertId;
    }

    public void setPropertyAdvertId(PropertyAdvert propertyAdvertId) {
        this.propertyAdvertId = propertyAdvertId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public LocalDateTime getDateTimeOfBid() {
        return dateTimeOfBid;
    }

    public void setDateTimeOfBid(LocalDateTime dateTimeOfBid) {
        this.dateTimeOfBid = dateTimeOfBid;
    }

    public Integer getAmountOfBid() {
        return amountOfBid;
    }

    public void setAmountOfBid(Integer amountOfBid) {
        this.amountOfBid = amountOfBid;
    }
}
