package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.*;

import java.util.List;

public class PropertyAdvertFormData {

    private Integer price;

    private List<Image> listOfImages;

    private Integer advertId;

    private PropertyType propertyType;

    private PropertyCondition propertyCondition;

    private ConstructionType constructionType;

    private ParkingType parkingType;

    private String name;

    private String address;

    private String district;

    private String street;

    private Integer area;

    private Integer numberOfRooms;

    private boolean elevator;

    private boolean balcony;

    private String description;

    public PropertyAdvertFormData(PropertyAdvert propertyAdvert) {
        this.price = propertyAdvert.getPrice();
        this.listOfImages = propertyAdvert.getListOfImages();
        this.advertId = propertyAdvert.getAdvertId();
        this.propertyType = propertyAdvert.getPropertyType();
        this.propertyCondition = propertyAdvert.getPropertyCondition();
        this.constructionType = propertyAdvert.getConstructionType();
        this.parkingType = propertyAdvert.getParkingType();
        this.name = propertyAdvert.getName();
        this.address = propertyAdvert.getAddress();
        this.district = propertyAdvert.getDistrict();
        this.street = propertyAdvert.getStreet();
        this.area = propertyAdvert.getArea();
        this.numberOfRooms = propertyAdvert.getNumberOfRooms();
        this.elevator = propertyAdvert.isElevator();
        this.balcony = propertyAdvert.isBalcony();
        this.description = propertyAdvert.getDescription();
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
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

    public PropertyCondition getPropertyCondition() {
        return propertyCondition;
    }

    public void setPropertyCondition(PropertyCondition propertyCondition) {
        this.propertyCondition = propertyCondition;
    }

    public ConstructionType getConstructionType() {
        return constructionType;
    }

    public void setConstructionType(ConstructionType constructionType) {
        this.constructionType = constructionType;
    }

    public ParkingType getParkingType() {
        return parkingType;
    }

    public void setParkingType(ParkingType parkingType) {
        this.parkingType = parkingType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
