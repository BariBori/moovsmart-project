package com.progmasters.moovsmart.dto.list;

import com.progmasters.moovsmart.domain.Bid;

import java.time.LocalDateTime;
import java.util.List;

public class MyBidList {

    LocalDateTime dateTimeOfBid;
    Double amountOfBid;

    String title;
    Integer advertId;
    String address;
    LocalDateTime startOfAuction;
    LocalDateTime endOfAuction;

    List<String> listOfImages;

    Double actualPrice;

    Integer numberOfBids;

    Long propertyId;

    LocalDateTime today;

    public MyBidList(Bid bid){
        this.dateTimeOfBid = bid.getDateTimeOfBid();
        this.amountOfBid = bid.getAmountOfBid();
        this.title = bid.getPropertyAdvertId().getTitle();
        this.advertId = bid.getPropertyAdvertId().getAdvertId();
        this.address = bid.getPropertyAdvertId().getAddress();
        this.startOfAuction = bid.getPropertyAdvertId().getStartOfAuction();
        this.endOfAuction = bid.getPropertyAdvertId().getEndOfAuction();
        this.listOfImages = bid.getPropertyAdvertId().getListOfImages();
        this.actualPrice = bid.getPropertyAdvertId().getActualPrice();
        this.numberOfBids = bid.getPropertyAdvertId().getListOfBids().size();
        this.propertyId = bid.getPropertyAdvertId().getId();
        this.today = LocalDateTime.now();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getListOfImages() {
        return listOfImages;
    }

    public void setListOfImages(List<String> listOfImages) {
        this.listOfImages = listOfImages;
    }

    public Double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public Integer getNumberOfBids() {
        return numberOfBids;
    }

    public void setNumberOfBids(Integer numberOfBids) {
        this.numberOfBids = numberOfBids;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }


    public LocalDateTime getToday() {
        return today;
    }

    public void setToday(LocalDateTime today) {
        this.today = today;
    }
}
