package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.*;

import java.util.List;

public class PropertyAdvertDetailsData {

    private Double price;

    private List<Image> listOfImages;

    private Integer advertId;

    private PropertyType propertyType;

    private PropertyConditionType propertyConditionType;

    private ParkingType parkingType;

    private String title;

    private String placeId;

    private String address;

    private String city;

    private String district;

    private String street;

    private Integer area;

    private Integer numberOfRooms;

    private boolean elevator;

    private boolean balcony;

    private String description;

    private AdvertStatusType advertStatus;

    public PropertyAdvertDetailsData(PropertyAdvert propertyAdvert) {
        this.price = propertyAdvert.getPrice();
        this.listOfImages = propertyAdvert.getListOfImages();
        this.advertId = propertyAdvert.getAdvertId();
        this.propertyType = propertyAdvert.getPropertyType();
        this.propertyConditionType = propertyAdvert.getPropertyConditionType();
        this.parkingType = propertyAdvert.getParkingType();
        this.title = propertyAdvert.getTitle();
        this.placeId = propertyAdvert.getPlaceId();
        this.address = propertyAdvert.getAddress();
        this.city = propertyAdvert.getCity();
        this.district = propertyAdvert.getDistrict();
        this.street = propertyAdvert.getStreet();
        this.area = propertyAdvert.getArea();
        this.numberOfRooms = propertyAdvert.getNumberOfRooms();
        this.elevator = propertyAdvert.isElevator();
        this.balcony = propertyAdvert.isBalcony();
        this.description = propertyAdvert.getDescription();
        this.advertStatus = propertyAdvert.getAdvertStatus();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Image> getListOfImages() {
        return listOfImages;
    }

    public void setListOfImages(List<Image> listOfImages) {
        this.listOfImages = listOfImages;
    }

    public Integer getAdvertId() {
        return advertId;
    }

    public void setAdvertId(Integer advertId) {
        this.advertId = advertId;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public PropertyConditionType getPropertyConditionType() {
        return propertyConditionType;
    }

    public void setPropertyConditionType(PropertyConditionType propertyConditionType) {
        this.propertyConditionType = propertyConditionType;
    }

    public ParkingType getParkingType() {
        return parkingType;
    }

    public void setParkingType(ParkingType parkingType) {
        this.parkingType = parkingType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public AdvertStatusType getAdvertStatus() {
        return advertStatus;
    }

    public void setAdvertStatus(AdvertStatusType advertStatus) {
        this.advertStatus = advertStatus;
    }
}