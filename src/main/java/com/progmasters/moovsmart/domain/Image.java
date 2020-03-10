package com.progmasters.moovsmart.domain;

import javax.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String imageUrl;

    @ManyToOne
    @JoinColumn
    private PropertyAdvert propertyAdvert;

    public Image(String imageUrl, PropertyAdvert propertyAdvert) {
        this.imageUrl = imageUrl;
        this.propertyAdvert = propertyAdvert;
    }

    public Image() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public PropertyAdvert getPropertyAdvert() {
        return propertyAdvert;
    }

    public void setPropertyAdvert(PropertyAdvert propertyAdvert) {
        this.propertyAdvert = propertyAdvert;
    }
}
