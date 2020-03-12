package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.PropertyAdvert;

public class PropertyAdvertListItem {

    private long id;

    private String title;

    private Integer numberOfRooms;

    private Double price;

    private String description;

    private Integer advertId;

    public PropertyAdvertListItem(PropertyAdvert propertyAdvert) {
        this.id = propertyAdvert.getId();
        this.title = propertyAdvert.getTitle();
        this.numberOfRooms = propertyAdvert.getNumberOfRooms();
        this.price = propertyAdvert.getPrice();
        this.description = propertyAdvert.getDescription();
        this.advertId = propertyAdvert.getAdvertId();
    }

    public PropertyAdvertListItem() {
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

    public String getDescription() {
        return description;
    }

    public Integer getAdvertId() {
        return advertId;
    }
}
