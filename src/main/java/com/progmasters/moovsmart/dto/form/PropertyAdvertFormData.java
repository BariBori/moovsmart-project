package com.progmasters.moovsmart.dto.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.progmasters.moovsmart.domain.*;

import java.time.LocalDateTime;
import java.util.List;

public class PropertyAdvertFormData {

    private long id;

    private Double price;

    private List<String> listOfImages;

    private Integer advertId;

    private PropertyType propertyType;

    private PropertyConditionType propertyConditionType;

    private ParkingType parkingType;

    private AdvertStatusType advertStatus;

    private String title;

    private String placeId;

    private Double longitude;

    private Double latitude;

    private String address;

    private String city;

    private String district;

    private String street;

    private Integer area;

    private Integer numberOfRooms;

    private boolean elevator;

    private boolean balcony;

    private String description;

    private LocalDateTime startOfAuction;

    private LocalDateTime endOfAuction;

    private Double actualPrice;

    public PropertyAdvertFormData() {
    }

    public PropertyAdvertFormData(PropertyAdvert propertyAdvert) {
        this.id = propertyAdvert.getId();
        this.price = propertyAdvert.getPrice();
        this.listOfImages = propertyAdvert.getListOfImages();
        this.advertId = propertyAdvert.getAdvertId();

        this.propertyType = propertyAdvert.getPropertyType();
        this.propertyConditionType = propertyAdvert.getPropertyConditionType();
        this.parkingType = propertyAdvert.getParkingType();
        this.advertStatus = propertyAdvert.getAdvertStatus();

        this.title = propertyAdvert.getTitle();
        this.address = propertyAdvert.getAddress();
        this.latitude = propertyAdvert.getLatitude();
        this.longitude = propertyAdvert.getLongitude();
        this.city = propertyAdvert.getCity();
        this.district = propertyAdvert.getDistrict();
        this.street = propertyAdvert.getStreet();
        this.area = propertyAdvert.getArea();
        this.numberOfRooms = propertyAdvert.getNumberOfRooms();
        this.elevator = propertyAdvert.isElevator();
        this.balcony = propertyAdvert.isBalcony();
        this.description = propertyAdvert.getDescription();
        this.placeId = propertyAdvert.getPlaceId();
        this.startOfAuction = propertyAdvert.getStartOfAuction();
        this.endOfAuction = propertyAdvert.getEndOfAuction();
        this.actualPrice = propertyAdvert.getPrice();
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

    public Double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getPlaceId() {
        return placeId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<String> getListOfImages() {
        return listOfImages;
    }

    public void setListOfImages(List<String> listOfImages) {
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


    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
