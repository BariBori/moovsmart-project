package com.progmasters.moovsmart.dto.list;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.progmasters.moovsmart.domain.PropertyAdvert;
import com.progmasters.moovsmart.dto.form.AdvertStatusTypeOption;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class PropertyAdvertListItem {

    private long id;

    private List<String> listOfImages;

    private String title;

    private Integer advertId;

    private Integer numberOfRooms;

    private Integer area;

    private Integer priceForSquareMeter;

    private Double price;

    private Double actualPrice;

    private String address;

    private AdvertStatusTypeOption advertStatus;

    private Double longitude;
    private Double latitude;


    private LocalDate createdAt;


    private LocalDate timeOfActivation;

    private LocalDateTime startOfAuction;
    private LocalDateTime endOfAuction;
    private LocalDateTime today;
    private ZoneId zoneId = ZoneId.of("Europe/Budapest");


    public PropertyAdvertListItem(PropertyAdvert propertyAdvert) {
        this.id = propertyAdvert.getId();
        this.title = propertyAdvert.getTitle();
        this.numberOfRooms = propertyAdvert.getNumberOfRooms();
        this.price = propertyAdvert.getPrice();
        this.advertId = propertyAdvert.getAdvertId();
        this.listOfImages = propertyAdvert.getListOfImages();
        this.area = propertyAdvert.getArea();
        this.address = propertyAdvert.getAddress();
        this.actualPrice = propertyAdvert.getActualPrice();
        this.priceForSquareMeter = (int) Math.round(actualPrice * 1000000 / area);
        this.advertStatus = new AdvertStatusTypeOption(propertyAdvert.getAdvertStatus());
        this.latitude = propertyAdvert.getLatitude();
        this.longitude = propertyAdvert.getLongitude();
        this.createdAt = propertyAdvert.getCreatedAt();
        this.timeOfActivation  =propertyAdvert.getTimeOfActivation();
        this.startOfAuction = propertyAdvert.getStartOfAuction();
        this.endOfAuction = propertyAdvert.getEndOfAuction();
        this.today = LocalDateTime.now(zoneId);

    }

    public PropertyAdvertListItem() {
    }

    public String getAddress() {
        return address;
    }

    public List<String> getListOfImages() {
        return listOfImages;
    }

    public Integer getArea() {
        return area;
    }

    public Integer getPriceForSquareMeter() {
        return priceForSquareMeter;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getAdvertId() {
        return advertId;
    }

    public AdvertStatusTypeOption getAdvertStatus() {
        return advertStatus;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getTimeOfActivation() {
        return timeOfActivation;
    }

    public void setTimeOfActivation(LocalDate timeOfActivation) {
        this.timeOfActivation = timeOfActivation;
    }

    public LocalDateTime getStartOfAuction() {
        return startOfAuction;
    }

    public LocalDateTime getEndOfAuction() {
        return endOfAuction;
    }

    public LocalDateTime getToday() {
        return today;
    }

    public Double getActualPrice() {
        return actualPrice;
    }
}
