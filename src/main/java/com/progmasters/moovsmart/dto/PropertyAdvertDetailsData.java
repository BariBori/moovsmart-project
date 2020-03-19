package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.*;

import java.util.List;

public class PropertyAdvertDetailsData {

    private long id;

    private Double price;

    private List<String> listOfImages;

    private Integer advertId;

    private PropertyTypeOption propertyType;

    private PropertyConditionOption propertyConditionType;

    private ParkingTypeOption parkingType;

    private AdvertStatusTypeOption advertStatus;

    private String title;

    private String placeId;

    private String address;

    private Double latitude;

    private Double longitude;

    private String city;

    private String district;

    private String street;

    private Integer area;

    private Integer numberOfRooms;

    private boolean elevator;

    private boolean balcony;

    private String description;



    private Long priceForSquareMeter;

    private String userName;

    public PropertyAdvertDetailsData(PropertyAdvert propertyAdvert) {
        this.id = propertyAdvert.getId();
        this.price = propertyAdvert.getPrice();
        this.listOfImages = propertyAdvert.getListOfImages();
        this.advertId = propertyAdvert.getAdvertId();

        this.advertStatus = new AdvertStatusTypeOption(propertyAdvert.getAdvertStatus());
        this.propertyType = new PropertyTypeOption(propertyAdvert.getPropertyType());
        this.propertyConditionType = new PropertyConditionOption(propertyAdvert.getPropertyConditionType());
        this.parkingType = new ParkingTypeOption(propertyAdvert.getParkingType());

        this.userName = propertyAdvert.getUser().getUserName();
        this.title = propertyAdvert.getTitle();
        this.placeId = propertyAdvert.getPlaceId();
        this.latitude = propertyAdvert.getLatitude();
        this.longitude = propertyAdvert.getLongitude();
        this.address = propertyAdvert.getAddress();
        this.city = propertyAdvert.getCity();
        this.district = propertyAdvert.getDistrict();
        this.street = propertyAdvert.getStreet();
        this.area = propertyAdvert.getArea();
        this.numberOfRooms = propertyAdvert.getNumberOfRooms();
        this.elevator = propertyAdvert.isElevator();
        this.balcony = propertyAdvert.isBalcony();
        this.description = propertyAdvert.getDescription();
        this.priceForSquareMeter = Math.round(price *1000000 / area);
    }

    public PropertyAdvertDetailsData() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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



        public PropertyTypeOption getPropertyType() {
        return propertyType;
    }


     public void setPropertyType(PropertyTypeOption propertyType) {
        this.propertyType = propertyType;
    }

    public PropertyConditionOption getPropertyConditionType() {
        return propertyConditionType;
    }

    public void setPropertyConditionType(PropertyConditionOption propertyConditionType) {
        this.propertyConditionType = propertyConditionType;
    }

    public ParkingTypeOption getParkingType() {
        return parkingType;
    }

    public void setParkingType(ParkingTypeOption parkingType) {
        this.parkingType = parkingType;
    }

    public AdvertStatusTypeOption getAdvertStatus() {
        return advertStatus;
    }

    public void setAdvertStatus(AdvertStatusTypeOption advertStatus) {
        this.advertStatus = advertStatus;
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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Long getPriceForSquareMeter() {
        return priceForSquareMeter;
    }

    public void setPriceForSquareMeter(Long priceForSquareMeter) {
        this.priceForSquareMeter = priceForSquareMeter;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
