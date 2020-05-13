package com.progmasters.moovsmart.dto.list;

import com.progmasters.moovsmart.domain.Bid;

import java.time.LocalDateTime;

public class MyBidList {

    LocalDateTime dateTimeOfBid;
    Double amountOfBid;

    String title;
    Integer advertId;
    LocalDateTime startOfAuction;
    LocalDateTime endOfAuction;

    public MyBidList(Bid bid){
        this.dateTimeOfBid = bid.getDateTimeOfBid();
        this.amountOfBid = bid.getAmountOfBid();
        this.title = bid.getPropertyAdvertId().getTitle();
        this.advertId = bid.getPropertyAdvertId().getAdvertId();
        this.startOfAuction = bid.getPropertyAdvertId().getStartOfAuction();
        this.endOfAuction = bid.getPropertyAdvertId().getEndOfAuction();

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAdvertId() {
        return advertId;
    }

    public void setAdvertId(Integer advertId) {
        this.advertId = advertId;
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

    public LocalDateTime getStartOfAuction() {
        return startOfAuction;
    }

    public void setStartOfAuction(LocalDateTime startOfAuction) {
        this.startOfAuction = startOfAuction;
    }

    public LocalDateTime getEndOfAuction() {
        return endOfAuction;
    }

    public void setEndOfAuction(LocalDateTime endOfAuction) {
        this.endOfAuction = endOfAuction;
    }
}
