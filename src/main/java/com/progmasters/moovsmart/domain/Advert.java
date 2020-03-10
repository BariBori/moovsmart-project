package com.progmasters.moovsmart.domain;

import com.progmasters.moovsmart.dto.AdvertFormData;

import javax.persistence.*;
import java.util.List;
import java.util.Random;

@Entity
public class Advert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Integer price;

    @OneToMany(mappedBy = "advert")
    private List<Image> listOfImages;

    private Integer advertId;

    @OneToOne
    private Property property;

    @OneToOne
    private User user;

    public Advert(AdvertFormData advertFormData) {
        this.price = advertFormData.getPrice();
        this.listOfImages = advertFormData.getListOfImages();
        this.advertId = new Random().nextInt(1000000);
        this.property = property;
        this.user = user;
    }

    public Advert() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
