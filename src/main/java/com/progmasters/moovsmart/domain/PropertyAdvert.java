package com.progmasters.moovsmart.domain;
import com.progmasters.moovsmart.dto.PropertyAdvertFormData;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Entity
public class PropertyAdvert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull
    private Integer price;

    @OneToMany(mappedBy = "propertyAdvert")
    @NotEmpty
    private List<Image> listOfImages;

    @Column
    private Integer advertId;

    @OneToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    @Enumerated(EnumType.STRING)
    private PropertyConditionType propertyConditionType;

    @Enumerated(EnumType.STRING)
    private PropertyConstructionType propertyConstructionType;

    @Enumerated(EnumType.STRING)
    private ParkingType parkingType;

    @Column
    @Size(min = 10, max = 50)
    private String title;

    @Column
    private String address;

    @Column
    private String city;

    @Column
    private String district;

    @Column
    private String street;

    @Column
    @NotNull
    private Integer area;

    @Column
    @Size(min = 1)
    private Integer numberOfRooms;

    @Column
    @NotNull
    private boolean elevator;

    @Column
    @NotNull
    private boolean balcony;

    @Column
    @Size(min = 20, max = 600)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column
    private AdvertStatusType advertStatus;

    @Column
    private LocalDate createdAt;

    @Column
    private LocalDate timeOfActivation;

        public PropertyAdvert(PropertyAdvertFormData propertyAdvertFormData) {
        this.price = propertyAdvertFormData.getPrice();
        this.listOfImages = propertyAdvertFormData.getListOfImages();
        this.advertStatus = propertyAdvertFormData.getAdvertStatus();
        this.createdAt = LocalDate.now();
        this.timeOfActivation = LocalDate.now();
        this.advertId = new Random().nextInt(1000000);
        this.propertyType = propertyAdvertFormData.getPropertyType();
        this.propertyConditionType = propertyAdvertFormData.getPropertyConditionType();
        this.propertyConstructionType = propertyAdvertFormData.getPropertyConstructionType();
        this.parkingType = propertyAdvertFormData.getParkingType();
        this.title = propertyAdvertFormData.getTitle();
        this.city = propertyAdvertFormData.getCity();
        this.address = propertyAdvertFormData.getAddress();
        this.district = propertyAdvertFormData.getDistrict();
        this.street = propertyAdvertFormData.getStreet();
        this.area = propertyAdvertFormData.getArea();
        this.numberOfRooms = propertyAdvertFormData.getNumberOfRooms();
        this.elevator = propertyAdvertFormData.isElevator();
        this.balcony = propertyAdvertFormData.isBalcony();
        this.description = propertyAdvertFormData.getDescription();
    }

    public PropertyAdvert() {
    }

    public AdvertStatusType getAdvertStatus() {
        return advertStatus;
    }

    public void setAdvertStatus(AdvertStatusType advertStatus) {
        this.advertStatus = advertStatus;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getTimeOfActivation() {
        return timeOfActivation;
    }

    public void setTimeOfActivation(LocalDate timeOfActivation) {
        this.timeOfActivation = timeOfActivation;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public PropertyConstructionType getPropertyConstructionType() {
        return propertyConstructionType;
    }

    public void setPropertyConstructionType(PropertyConstructionType propertyConstructionType) {
        this.propertyConstructionType = propertyConstructionType;
    }



    public ParkingType getParkingType() {
        return parkingType;
    }

    public void setParkingType(ParkingType parkingType) {
        this.parkingType = parkingType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
