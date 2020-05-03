package com.progmasters.moovsmart.dto.form;

import com.progmasters.moovsmart.domain.Bid;

import java.time.LocalDateTime;

public class BidFormData {

    private Double amountOfBid;

    public BidFormData(Bid bid) {
        this.amountOfBid = bid.getAmountOfBid();
    }

    public BidFormData() {
    }

    public Double getAmountOfBid() {
        return amountOfBid;
    }

    public void setAmountOfBid(Double amountOfBid) {
        this.amountOfBid = amountOfBid;
    }
}
