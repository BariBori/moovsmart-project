package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.Image;

import java.util.List;

public class AdvertFormData {

    private Integer price;

    private List<Image> listOfImages;

       public AdvertFormData(Integer price, List<Image> listOfImages) {
        this.price = price;
        this.listOfImages = listOfImages;
    }

    public AdvertFormData() {
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
