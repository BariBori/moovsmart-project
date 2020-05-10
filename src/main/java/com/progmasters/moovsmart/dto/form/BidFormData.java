package com.progmasters.moovsmart.dto.form;

import com.progmasters.moovsmart.domain.Bid;

import java.time.LocalDateTime;

public class BidFormData {

    private Double amountOfBid;
    private Long propertyAdvertId;

    public BidFormData(Bid bid) {
        this.amountOfBid = bid.getAmountOfBid();
        this.propertyAdvertId = bid.getPropertyAdvertId().getId();

    }

    public BidFormData() {
    }

    public Double getAmountOfBid() {
        return amountOfBid;
    }

    public void setAmountOfBid(Double amountOfBid) {
        this.amountOfBid = amountOfBid;
    }

    public Long getPropertyAdvertId() {
        return propertyAdvertId;
    }

    public void setPropertyAdvertId(Long propertyAdvertId) {
        this.propertyAdvertId = propertyAdvertId;
    }
}

