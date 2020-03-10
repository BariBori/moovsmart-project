package com.progmasters.moovsmart.domain;

import javax.persistence.*;

@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String district;

    @Column
    private String street;

    @Column
    private Integer area;

    @Column
    private Integer numberOfRooms;

    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    @Enumerated(EnumType.STRING)
    private PropertyCondition propertyCondition;

    @Enumerated(EnumType.STRING)
    private ConstructionType constructionType;

    @Enumerated(EnumType.STRING)
    private Parking parking;

    @Column
    private boolean elevator;

    @Column
    private boolean balcony;

    @Column
    private String description;

    @OneToOne
    private Advert advert;

    public Property(String name, String address, String district, String street, Integer area, Integer numberOfRooms,
                    PropertyType propertyType, PropertyCondition propertyCondition, ConstructionType constructionType,
                    Parking parking, boolean elevator, boolean balcony, String description, Advert advert) {
        this.name = name;
        this.address = address;
        this.district = district;
        this.street = street;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.propertyType = propertyType;
        this.propertyCondition = propertyCondition;
        this.constructionType = constructionType;
        this.parking = parking;
        this.elevator = elevator;
        this.balcony = balcony;
        this.description = description;
        this.advert = advert;
    }

    public Property() {
    }

    public Advert getAdvert() {
        return advert;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public PropertyCondition getPropertyCondition() {
        return propertyCondition;
    }

    public void setPropertyCondition(PropertyCondition propertyCondition) {
        this.propertyCondition = propertyCondition;
    }

    public ConstructionType getConstructionType() {
        return constructionType;
    }

    public void setConstructionType(ConstructionType constructionType) {
        this.constructionType = constructionType;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
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
}
