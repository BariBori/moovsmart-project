package com.progmasters.moovsmart.domain;

import com.progmasters.moovsmart.domain.user.User;
import com.progmasters.moovsmart.dto.form.PropertyAdvertFormData;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Entity
public class PropertyAdvert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

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
    @Min(0)
    @Max(1000)
    private Integer area;

    @Column
    @NotNull
    @Min(0)
    @Max(30)
    private Integer numberOfRooms;

    @Column
    @NotNull
    @Min(0)
    @Max(1000)
    private Double price;

    @Column
    private boolean elevator;

    @Column
    private boolean balcony;

    @Column
    @Size(min = 20, max = 600)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column
    private AdvertStatusType advertStatus;

    @Column
    private LocalDate createdAt;

    @Enumerated(EnumType.STRING)
    @Column
    private AuctionStatusType auctionStatusType;

    @Column
    private LocalDate timeOfActivation;

    @ManyToOne
    private User user;

    @ManyToMany(mappedBy = "savedAdverts")
    private List<User> likes = new ArrayList<>();

    private String userName;

    @Column
    private LocalDateTime startOfAuction;

    @Column
    private LocalDateTime endOfAuction;

    @Column
    private Double actualPrice;


    @OneToMany(mappedBy = "propertyAdvertId")
    private List<Bid> listOfBids = new ArrayList<>();

    public PropertyAdvert(PropertyAdvertFormData propertyAdvertFormData, User user) {
        this.id = propertyAdvertFormData.getId();
        this.userName = user.getUserName();
        this.user = user;
        this.price = propertyAdvertFormData.getPrice();
        this.listOfImages = propertyAdvertFormData.getListOfImages();
        this.placeId = propertyAdvertFormData.getPlaceId();
        this.latitude = propertyAdvertFormData.getLatitude();
        this.longitude = propertyAdvertFormData.getLongitude();
        this.createdAt = LocalDate.now().plusDays(1);
        this.timeOfActivation = null;
        this.advertId = new Random().nextInt(1000000);

        this.advertStatus = AdvertStatusType.FORAPPROVAL;
        this.propertyType = propertyAdvertFormData.getPropertyType();
        this.propertyConditionType = propertyAdvertFormData.getPropertyConditionType();
        this.parkingType = propertyAdvertFormData.getParkingType();

        if (propertyAdvertFormData.getStartOfAuction() == null && propertyAdvertFormData.getEndOfAuction() == null) {
            this.auctionStatusType = AuctionStatusType.INACTIVE;
        } else {
            this.auctionStatusType = AuctionStatusType.ACTIVE;
        }

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
        this.actualPrice = propertyAdvertFormData.getPrice();
        this.startOfAuction = propertyAdvertFormData.getStartOfAuction();
        this.endOfAuction = propertyAdvertFormData.getEndOfAuction();

}

    public PropertyAdvert() {
    }


    public LocalDateTime getStartOfAuction() {
        return startOfAuction;
    }

    public void setStartOfAuction(LocalDateTime startOfAuction) {
        this.startOfAuction = startOfAuction;
    }

    public LocalDateTime getEndOfAuction() {
        return endOfAuction;
    }

    public void setEndOfAuction(LocalDateTime endOfAuction) {
        this.endOfAuction = endOfAuction;
    }

    public List<Bid> getListOfBids() {
        return listOfBids;
    }

    public void setListOfBids(List<Bid> listOfBids) {
        this.listOfBids = listOfBids;
    }

    public Double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(Double actualPrice) {
        this.actualPrice = actualPrice;
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

    public void setListOfImages(List<String> listOfImages) {
        this.listOfImages = listOfImages;
    }

    public Integer getAdvertId() {
        return advertId;
    }

    public void setAdvertId(Integer advertId) {
        this.advertId = advertId;
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

    public ParkingType getParkingType() {
        return parkingType;
    }

    public void setParkingType(ParkingType parkingType) {
        this.parkingType = parkingType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<User> getLikes() {
        return likes;
    }

    public void setLikes(List<User> likes) {
        this.likes = likes;
    }

    public AuctionStatusType getAuctionStatusType() {
        return auctionStatusType;
    }

    public void setAuctionStatusType(AuctionStatusType auctionStatusType) {
        this.auctionStatusType = auctionStatusType;
    }
}
