package com.progmasters.moovsmart.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PropertyForm {

    @NotNull(message = "Property name cannot be empty!")
    @Size(min = 1, max = 200, message = "Property name must be between 1 and 200 characters!")
    private String name;

    @Min(value = 1, message = "Number of rooms must be between 1 and 12!")
    @Max(value = 12, message = "Number of rooms must be between 1 and 12!")
    private int numberOfRooms;

    private int price;
    private String description;
    private String imageUrl;

    PropertyForm() {

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
