package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.domain.*;
import com.progmasters.moovsmart.domain.user.User;
import com.progmasters.moovsmart.domain.user.UserDetailsImpl;
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

    public void saveAdvert(PropertyAdvertFormData propertyAdvertFormData, UserDetailsImpl userDetails) {
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
        return advertRepository.findByOrderByTimeOfActivationDesc().stream()
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


    public PropertyAdvert updateProperty(PropertyAdvertDetailsData propertyAdvertDetailsData, Long id) {
        Optional<PropertyAdvert> propertyAdvertOptional = advertRepository.findById(id);
        if(propertyAdvertOptional.isPresent()){
            PropertyAdvert propertyAdvert = propertyAdvertOptional.get();
            updateValues(propertyAdvertDetailsData, propertyAdvert);
            advertRepository.save(propertyAdvert);
            return propertyAdvert;
        } else {
            return null;
        }
    }


    private void updateValues(PropertyAdvertDetailsData propertyAdvertDetailsData, PropertyAdvert propertyAdvert){

        propertyAdvert.setUserName(propertyAdvertDetailsData.getUserName());

        propertyAdvert.setAdvertId(propertyAdvertDetailsData.getAdvertId());

        propertyAdvert.setTitle(propertyAdvertDetailsData.getTitle());

        propertyAdvert.setPrice(propertyAdvertDetailsData.getPrice());
        propertyAdvert.setArea(propertyAdvertDetailsData.getArea());
        propertyAdvert.setNumberOfRooms(propertyAdvertDetailsData.getNumberOfRooms());

        propertyAdvert.setAdvertStatus(AdvertStatusType.valueOf(propertyAdvertDetailsData.getAdvertStatus().getDisplayName()));
        propertyAdvert.setPropertyType(PropertyType.valueOf(propertyAdvertDetailsData.getPropertyType().getDisplayName()));
        propertyAdvert.setPropertyConditionType(PropertyConditionType.valueOf(propertyAdvertDetailsData.getPropertyConditionType().getDisplayName()));
        propertyAdvert.setParkingType(ParkingType.valueOf(propertyAdvertDetailsData.getParkingType().getDisplayName()));

        propertyAdvert.setPlaceId(propertyAdvertDetailsData.getPlaceId());
        propertyAdvert.setLatitude(propertyAdvertDetailsData.getLatitude());
        propertyAdvert.setLongitude(propertyAdvertDetailsData.getLongitude());
        propertyAdvert.setAddress(propertyAdvertDetailsData.getAddress());
        propertyAdvert.setCity(propertyAdvertDetailsData.getCity());
        propertyAdvert.setDistrict(propertyAdvertDetailsData.getDistrict());
        propertyAdvert.setStreet(propertyAdvertDetailsData.getStreet());


        propertyAdvert.setElevator(propertyAdvertDetailsData.isElevator());
        propertyAdvert.setBalcony(propertyAdvertDetailsData.isBalcony());

        propertyAdvert.setDescription(propertyAdvertDetailsData.getDescription());

        propertyAdvert.setListOfImages(propertyAdvertDetailsData.getListOfImages());

    }
}
