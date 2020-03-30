package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.AdvertStatusType;
import com.progmasters.moovsmart.domain.PropertyConditionType;
import com.progmasters.moovsmart.domain.PropertyType;

public class FilterPropertyAdvert {

    private String city;
    private Double minPrice;
    private Double maxPrice;
    private Integer minArea;
    private Integer maxArea;
    private Integer minRooms;
    private Integer maxRooms;
    private AdvertStatusType advertStatusType;
    private PropertyType propertyType;
    private PropertyConditionType propertyConditionType;


    public FilterPropertyAdvert(String city, Double minPrice, Double maxPrice, Integer minArea, Integer maxArea,
                                Integer minRooms, Integer maxRooms, PropertyType propertyType,
                                PropertyConditionType propertyConditionType) {
        this.city = city;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minArea = minArea;
        this.maxArea = maxArea;
        this.minRooms = minRooms;
        this.maxRooms = maxRooms;
        this.advertStatusType = AdvertStatusType.FORAPPROVAL;
        this.propertyType = propertyType;
        this.propertyConditionType = propertyConditionType;
    }



    public AdvertStatusType getAdvertStatusType() {
        return advertStatusType;
    }

    public void setAdvertStatusType(AdvertStatusType advertStatusType) {
        this.advertStatusType = advertStatusType;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Integer getMinArea() {
        return minArea;
    }

    public void setMinArea(Integer minArea) {
        this.minArea = minArea;
    }

    public Integer getMaxArea() {
        return maxArea;
    }

    public void setMaxArea(Integer maxArea) {
        this.maxArea = maxArea;
    }

    public Integer getMinRooms() {
        return minRooms;
    }

    public void setMinRooms(Integer minRooms) {
        this.minRooms = minRooms;
    }

    public Integer getMaxRooms() {
        return maxRooms;
    }

    public void setMaxRooms(Integer maxRooms) {
        this.maxRooms = maxRooms;
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
}
