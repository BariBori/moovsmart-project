package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.Image;

import java.util.List;

public class PropertyAdvertFormData {

    private Integer price;

    private List<Image> listOfImages;

       public PropertyAdvertFormData(Integer price, List<Image> listOfImages) {
        this.price = price;
        this.listOfImages = listOfImages;
    }

    public PropertyAdvertFormData() {
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

}
