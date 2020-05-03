package com.progmasters.moovsmart.dto.list;

import com.progmasters.moovsmart.domain.Bid;

import java.time.LocalDateTime;

public class BidListItem {

    private long id;
    private LocalDateTime dateTimeOfBid;
    private Double amountOfBid;
    private String userName;


    public BidListItem(Bid bid){
        this.id = bid.getId();
        this.dateTimeOfBid = bid.getDateTimeOfBid();
        this.amountOfBid = bid.getAmountOfBid();
        this.userName = bid.getUserId().getUserName();
    }

    public BidListItem() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
