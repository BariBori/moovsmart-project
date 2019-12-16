package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.Property;

public class PropertyDetails {

    private long id;
    private String name;
    private int numberOfRooms;
    private int price;
    private String description;
    private String imageUrl;

    public PropertyDetails() {
    }

    public PropertyDetails(Property property) {
        this.id = property.getId();
        this.name = property.getName();
        this.numberOfRooms = property.getNumberOfRooms();
        this.price = property.getPrice();
        this.description = property.getDescription();
        this.imageUrl = property.getImageUrl();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
