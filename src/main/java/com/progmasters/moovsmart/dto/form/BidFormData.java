package com.progmasters.moovsmart.dto.form;

import com.progmasters.moovsmart.domain.Bid;

import java.time.LocalDateTime;

public class BidFormData {


    private LocalDateTime dateTimeOfBid;

    private Integer amountOfBid;

    public BidFormData(Bid bid) {
        this.dateTimeOfBid = bid.getDateTimeOfBid() ;
        this.amountOfBid = bid.getAmountOfBid();
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
