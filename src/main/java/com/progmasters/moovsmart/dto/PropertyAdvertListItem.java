package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.AdvertStatusType;
import com.progmasters.moovsmart.domain.PropertyAdvert;

import java.util.List;

public class PropertyAdvertListItem {

    private long id;

    private List<String> listOfImages;

    private String title;

    private Integer advertId;

    private Integer numberOfRooms;

    private Integer area;

    private Long priceForSquareMeter;

    private Double price;

    private String address;

    private AdvertStatusTypeOption advertStatus;

    public PropertyAdvertListItem(PropertyAdvert propertyAdvert) {
        this.id = propertyAdvert.getId();
        this.title = propertyAdvert.getTitle();
        this.numberOfRooms = propertyAdvert.getNumberOfRooms();
        this.price = propertyAdvert.getPrice();
        this.advertId = propertyAdvert.getAdvertId();
        this.listOfImages = propertyAdvert.getListOfImages();
        this.area = propertyAdvert.getArea();
        this.address = propertyAdvert.getAddress();
        this.priceForSquareMeter = Math.round(price * 1000000 / area);
        this.advertStatus = new AdvertStatusTypeOption(propertyAdvert.getAdvertStatus());
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

    public Long getPriceForSquareMeter() {
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
}
