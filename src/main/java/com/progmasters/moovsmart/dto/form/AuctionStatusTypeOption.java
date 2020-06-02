package com.progmasters.moovsmart.dto.form;

import com.progmasters.moovsmart.domain.AuctionStatusType;

public class AuctionStatusTypeOption {

    private String name;
    private String displayName;

    public AuctionStatusTypeOption() {
    }

    public AuctionStatusTypeOption(AuctionStatusType auctionStatusType){
        this.name = auctionStatusType.toString();
        this.displayName = auctionStatusType.getDisplayName();
    }

    public String getName() {
        return name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
