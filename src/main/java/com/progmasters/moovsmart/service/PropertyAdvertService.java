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
import java.util.ArrayList;
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
        this.userRepository = userRepository;
        this.advertRepository = advertRepository;
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
                .map(PropertyAdvertListItem::new).collect(Collectors.toList());
    }

    public List<PropertyCity> listCities(){
        return advertRepository.findAllCities().stream()
                .map(PropertyCity::new).collect(Collectors.toList());
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

    public List<PropertyAdvertListItem> getFilteredPropertyAdverts(FilterPropertyAdvert filterPropertyAdvert) {
        String city = filterPropertyAdvert.getCity();
        Double minPrice = filterPropertyAdvert.getMinPrice();
        Double maxPrice = filterPropertyAdvert.getMaxPrice();
        Integer minArea = filterPropertyAdvert.getMinArea();
        Integer maxArea = filterPropertyAdvert.getMaxArea();
        Integer minRooms = filterPropertyAdvert.getMinRooms();
        Integer maxRooms = filterPropertyAdvert.getMaxRooms();
        AdvertStatusType advertStatusType = filterPropertyAdvert.getAdvertStatusType();
        PropertyType propertyType = filterPropertyAdvert.getPropertyType();
        PropertyConditionType propertyConditionType = filterPropertyAdvert.getPropertyConditionType();
        List<PropertyAdvert> filteredPropertyAdverts = advertRepository.findFilteredPropertyAdverts(city, minPrice, maxPrice, minArea, maxArea,
                minRooms, maxRooms, propertyType, propertyConditionType, advertStatusType);
        List<PropertyAdvertListItem> filteredList = new ArrayList<>();
        for (PropertyAdvert filteredPropertyAdvert : filteredPropertyAdverts) {
            filteredList.add(new PropertyAdvertListItem(filteredPropertyAdvert));
        }
        return filteredList;
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

}
