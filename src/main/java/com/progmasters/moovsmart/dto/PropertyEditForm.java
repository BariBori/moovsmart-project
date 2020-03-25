package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.*;

import java.util.Random;

public class PropertyEditForm {
    private long id;

    private Double price;

    private Integer advertId;

    private String propertyType;
    private String propertyConditionType;
    private String parkingType;

    private Integer area;

    private Integer numberOfRooms;

    private boolean elevator;

    private boolean balcony;

    private String description;

    public PropertyEditForm() {
    }

    public PropertyEditForm(PropertyAdvert propertyAdvert) {
        this.id = propertyAdvert.getId();
        this.price = propertyAdvert.getPrice();
        this.advertId = propertyAdvert.getAdvertId();
        this.propertyType = propertyAdvert.getPropertyType().getDisplayName();
        this.propertyConditionType = propertyAdvert.getPropertyConditionType().getDisplayName();
        this.parkingType = propertyAdvert.getParkingType().getDisplayName();
        this.area = propertyAdvert.getArea();
        this.numberOfRooms = propertyAdvert.getNumberOfRooms();
        this.elevator = propertyAdvert.isElevator();
        this.balcony = propertyAdvert.isBalcony();
        this.description = propertyAdvert.getDescription();
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

    public void setAdvertId(Integer advertId) {
        this.advertId = new Random().nextInt(1000000);;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public String getPropertyConditionType() {
        return propertyConditionType;
    }

    public void setPropertyConditionType(String propertyConditionType) {
        this.propertyConditionType = propertyConditionType;
    }

    public String getParkingType() {
        return parkingType;
    }

    public void setParkingType(String parkingType) {
        this.parkingType = parkingType;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Integer numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public boolean isElevator() {
        return elevator;
    }

    public void setElevator(boolean elevator) {
        this.elevator = elevator;
    }

    public boolean isBalcony() {
        return balcony;
    }

    public void setBalcony(boolean balcony) {
        this.balcony = balcony;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
