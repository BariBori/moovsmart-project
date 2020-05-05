package com.progmasters.moovsmart.domain;

import com.progmasters.moovsmart.domain.user.User;
import com.progmasters.moovsmart.dto.form.BidFormData;

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
    private Double amountOfBid;

    public Bid() {
    }

    public Bid(PropertyAdvert propertyAdvertId, User userId, BidFormData bidFormData) {
        this.propertyAdvertId = propertyAdvertId;
        this.userId = userId;
        this.dateTimeOfBid = LocalDateTime.now().plusDays(1);
        this.amountOfBid = bidFormData.getAmountOfBid();
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


    public Double getAmountOfBid() {
        return amountOfBid;
    }

    public void setAmountOfBid(Double amountOfBid) {
        this.amountOfBid = amountOfBid;
    }
}
