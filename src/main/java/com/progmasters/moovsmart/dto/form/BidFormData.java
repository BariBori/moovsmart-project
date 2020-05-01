package com.progmasters.moovsmart.dto.form;

import com.progmasters.moovsmart.domain.Bid;

import java.time.LocalDateTime;

public class BidFormData {

    private Integer amountOfBid;

    public BidFormData(Bid bid) {
        this.amountOfBid = bid.getAmountOfBid();
    }

    public BidFormData() {
    }

    public Integer getAmountOfBid() {
        return amountOfBid;
    }

    public void setAmountOfBid(Integer amountOfBid) {
        this.amountOfBid = amountOfBid;
    }
}
