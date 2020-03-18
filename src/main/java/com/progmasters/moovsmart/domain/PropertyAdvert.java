package com.progmasters.moovsmart.domain;

import com.progmasters.moovsmart.dto.PropertyAdvertFormData;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
public class PropertyAdvert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull
    private Double price;

    @ElementCollection
    @CollectionTable(name = "images")
    private List<String> listOfImages = new ArrayList<>();

    @Column
    private Integer advertId;

    @Enumerated(EnumType.STRING)
    @Column
    private PropertyType propertyType;

    @Enumerated(EnumType.STRING)
    @Column
    private PropertyConditionType propertyConditionType;

    @Enumerated(EnumType.STRING)
    @Column
    private ParkingType parkingType;

    @Column
    @Size(min = 10, max = 50)
    private String title;

    @Column
    private Double longitude;

    @Column
    private Double latitude;

    @Column
    private String placeId;

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
    @NotNull
    private Integer numberOfRooms;

    @Column
    //@NotNull
    private boolean elevator;

    @Column
    //@NotNull
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

    @ManyToOne
    private User user;

    private String userName;

    public PropertyAdvert(PropertyAdvertFormData propertyAdvertFormData, User user) {
        this.id = propertyAdvertFormData.getId();
        this.userName = user.getUserName();
        this.user = user;
        this.price = propertyAdvertFormData.getPrice();
        this.advertStatus = propertyAdvertFormData.getAdvertStatus();
        this.listOfImages = propertyAdvertFormData.getListOfImages();
        this.placeId = propertyAdvertFormData.getPlaceId();
        this.latitude = propertyAdvertFormData.getLatitude();
        this.longitude = propertyAdvertFormData.getLongitude();
        this.createdAt = LocalDate.now();
        this.timeOfActivation = LocalDate.now();
        this.advertId = new Random().nextInt(1000000);
        this.propertyType = propertyAdvertFormData.getPropertyType();
        this.propertyConditionType = propertyAdvertFormData.getPropertyConditionType();
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

    public String getPlaceId() {
        return placeId;
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

    public LocalDate getTimeOfActivation() {
        return timeOfActivation;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<String> getListOfImages() {
        return listOfImages;
    }


    public Integer getAdvertId() {
        return advertId;
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


    public PropertyConditionType getPropertyConditionType() {
        return propertyConditionType;
    }


    public ParkingType getParkingType() {
        return parkingType;
    }


    public String getTitle() {
        return title;
    }


    public String getAddress() {
        return address;
    }


    public String getDistrict() {
        return district;
    }


    public String getStreet() {
        return street;
    }

    public Integer getArea() {
        return area;
    }


    public Integer getNumberOfRooms() {
        return numberOfRooms;
    }


    public boolean isElevator() {
        return elevator;
    }


    public boolean isBalcony() {
        return balcony;
    }


    public String getDescription() {
        return description;
    }


    public String getCity() {
        return city;
    }

    public Double getLongitude() {
        return longitude;
    }


    public Double getLatitude() {
        return latitude;
    }


}
