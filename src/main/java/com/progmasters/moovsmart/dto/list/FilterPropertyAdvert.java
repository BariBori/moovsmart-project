package com.progmasters.moovsmart.dto.list;

import com.progmasters.moovsmart.domain.AdvertStatusType;
import com.progmasters.moovsmart.domain.PropertyConditionType;
import com.progmasters.moovsmart.domain.PropertyType;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class FilterPropertyAdvert {

    private String city;

    @Min(0)
    @Max(1000000000)
    private Double minPrice;

    @Min(0)
    @Max(1000000000)
    private Double maxPrice;

    @Min(0)
    @Max(1000)
    private Integer minArea;

    @Min(0)
    @Max(1000)
    private Integer maxArea;

    @Min(0)
    @Max(30)
    private Integer minRooms;

    @Min(0)
    @Max(30)
    private Integer maxRooms;

    private AdvertStatusType advertStatusType;
    private PropertyType propertyType;
    private PropertyConditionType propertyConditionType;


    private Double latitude;
    private Double longitude;


    public FilterPropertyAdvert(String city, Double minPrice, Double maxPrice, Integer minArea, Integer maxArea,
                                Integer minRooms, Integer maxRooms, PropertyType propertyType,
                                PropertyConditionType propertyConditionType, Double latitude, Double longitude) {
        this.city = city;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.minArea = minArea;
        this.maxArea = maxArea;
        this.minRooms = minRooms;
        this.maxRooms = maxRooms;
        this.latitude = latitude;
        this.longitude = longitude;
        this.advertStatusType = AdvertStatusType.FORAPPROVAL;
        this.propertyType = propertyType;
        this.propertyConditionType = propertyConditionType;
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

    public AdvertStatusType getAdvertStatusType() {
        return advertStatusType;
    }

    public void setAdvertStatusType(AdvertStatusType advertStatusType) {
        this.advertStatusType = advertStatusType;
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

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
