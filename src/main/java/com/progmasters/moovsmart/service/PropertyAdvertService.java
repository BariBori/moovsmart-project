package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.domain.*;
import com.progmasters.moovsmart.domain.user.User;
import com.progmasters.moovsmart.domain.user.UserIdentifier;
import com.progmasters.moovsmart.dto.*;
import com.progmasters.moovsmart.repository.AdvertRepository;
import com.progmasters.moovsmart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PropertyAdvertService {

    private AdvertRepository advertRepository;
    private UserRepository userRepository;

    @Autowired
    public PropertyAdvertService(AdvertRepository advertRepository, UserRepository userRepository) {
        this.advertRepository = advertRepository;
        this.userRepository = userRepository;
    }

    public void saveAdvert(PropertyAdvertFormData propertyAdvertFormData, UserIdentifier userDetails) {
        Optional<User> user = userRepository.findByUserName(userDetails.getUsername());
        if(user.isPresent()) {
            User chosenUser = user.get();
            PropertyAdvert propertyAdvert = new PropertyAdvert(propertyAdvertFormData, chosenUser);
            this.advertRepository.save(propertyAdvert);
        }
    }

    public PropertyAdvertInitFormData createPropertyAdvertFormInitData() {
        return new PropertyAdvertInitFormData(
                Arrays.stream(PropertyType.values()).map(PropertyTypeOption::new).collect(Collectors.toList()),
                Arrays.stream(PropertyConditionType.values()).map(PropertyConditionOption::new).collect(Collectors.toList()),
                Arrays.stream(ParkingType.values()).map(ParkingTypeOption::new).collect(Collectors.toList())
                //Arrays.stream(AdvertStatusType.values()).map(AdvertStatusTypeOption::new).collect(Collectors.toList())
                );
    }

    public List<PropertyAdvertListItem> listPropertyAdverts() {
        return advertRepository.findPropertyAdvertsByAdvertStatus_FORAPPROVAL().stream()
                .map(propertyAdvert -> new PropertyAdvertListItem(propertyAdvert)).collect(Collectors.toList());
    }

    public List<PropertyCity> listCities(){
        return advertRepository.findAllCities().stream()
                .map(propertyAdvert -> new PropertyCity(propertyAdvert)).collect(Collectors.toList());
    }

    public boolean archivePropertyAdvert(Long id) {
        boolean result = false;
        Optional<PropertyAdvert> propertyAdvertOptional = advertRepository.findById(id);
        if (propertyAdvertOptional.isPresent()) {
            PropertyAdvert propertyAdvert = propertyAdvertOptional.get();
            propertyAdvert.setAdvertStatus(AdvertStatusType.ARCHIVE);
            result = true;
        }
        return result;
    }

    public PropertyAdvertDetailsData getPropertyAdvertDetails(Long id) {
        PropertyAdvert propertyAdvert = advertRepository.findById(id).orElseThrow((() -> new EntityNotFoundException("Advert with id: " + id + " not found!")));
        return new PropertyAdvertDetailsData(propertyAdvert);
    }


    public boolean updateProperty(PropertyEditForm propertyEditForm, Long id) {
        Optional<PropertyAdvert> propertyAdvertOptional = advertRepository.findById(id);
        if(propertyAdvertOptional.isPresent()){
            PropertyAdvert propertyAdvert = propertyAdvertOptional.get();
            updateValues(propertyEditForm, propertyAdvert);
            return true;
        } else {
            return false;
        }
    }


    private void updateValues(PropertyEditForm propertyEditForm, PropertyAdvert propertyAdvert){
        propertyAdvert.setPrice(propertyEditForm.getPrice());
        propertyAdvert.setArea(propertyEditForm.getArea());
        propertyAdvert.setNumberOfRooms(propertyEditForm.getNumberOfRooms());
        propertyAdvert.setPropertyType(PropertyType.valueOf(propertyEditForm.getPropertyType()));
        propertyAdvert.setPropertyConditionType(PropertyConditionType.valueOf(propertyEditForm.getPropertyConditionType()));
        propertyAdvert.setParkingType(ParkingType.valueOf(propertyEditForm.getParkingType()));
        propertyAdvert.setElevator(propertyEditForm.isElevator());
        propertyAdvert.setBalcony(propertyEditForm.isBalcony());
        propertyAdvert.setDescription(propertyEditForm.getDescription());
        this.advertRepository.save(propertyAdvert);
    }

//
//    public List<PropertyAdvertListItem> getFilteredPropertyAdverts(FilterPropertyAdvert filterPropertyAdvert) {
//        return advertRepository.findPropertyAdvertsByCity(filterPropertyAdvert.getCity())
//                .stream()
//                .filter(getFilteredPrice(filterPropertyAdvert.getMinPrice(), filterPropertyAdvert.getMaxPrice()))
//                .filter(getFilteredArea(filterPropertyAdvert.getMinArea(), filterPropertyAdvert.getMayArea()))
//                .filter(getFilteredRoomNumber(filterPropertyAdvert.getMinRooms(), filterPropertyAdvert.getMaxRooms()))
//                .filter(getFilteredPropertyType(filterPropertyAdvert.getPropertyType()))
//                .filter(getFilteredPropertyConditionType(filterPropertyAdvert.getPropertyConditionType()))
//                .map(PropertyAdvertListItem::new)
//                .collect(Collectors.toList());
//    }
//
//    public Predicate<PropertyAdvert> getFilteredPrice (Double minPrice, Double maxPrice) {
//            return (Predicate<PropertyAdvert>) advertRepository.findPropertyAdvertsByPriceIsBetween(minPrice, maxPrice)
//                    .stream()
//                    .map(PropertyAdvert::getPrice)
//                    .collect(Collectors.toList());
//    }
//
//    public Predicate<PropertyAdvert> getFilteredArea (Integer minArea, Integer maxArea) {
//        return (Predicate<PropertyAdvert>) advertRepository.findPropertyAdvertsByAreaIsBetween(minArea, maxArea)
//                .stream()
//                .map(PropertyAdvert::getArea)
//                .collect(Collectors.toList());
//    }
//
//    public Predicate<PropertyAdvert> getFilteredRoomNumber (Integer minRoom, Integer maxRoom) {
//        return (Predicate<PropertyAdvert>) advertRepository.findPropertyAdvertsByAreaIsBetween(minRoom, maxRoom)
//                .stream()
//                .map(PropertyAdvert::getNumberOfRooms)
//                .collect(Collectors.toList());
//    }
//
//    public Predicate<PropertyAdvert> getFilteredPropertyType (PropertyType propertyType) {
//        return (Predicate<PropertyAdvert>) advertRepository.findPropertyAdvertsByPropertyType(propertyType)
//                .stream()
//                .map(PropertyAdvert::getPropertyType)
//                .collect(Collectors.toList());
//    }
//
//    public Predicate<PropertyAdvert> getFilteredPropertyConditionType (PropertyConditionType propertyConditionType) {
//        return (Predicate<PropertyAdvert>) advertRepository.findPropertyAdvertsByPropertyConditionType(propertyConditionType)
//                .stream()
//                .map(PropertyAdvert::getPropertyConditionType)
//                .collect(Collectors.toList());
//    }

}
