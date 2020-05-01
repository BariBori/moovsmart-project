package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.domain.*;
import com.progmasters.moovsmart.domain.user.User;
import com.progmasters.moovsmart.domain.user.UserIdentifier;
import com.progmasters.moovsmart.dto.form.ParkingTypeOption;
import com.progmasters.moovsmart.dto.form.PropertyConditionOption;
import com.progmasters.moovsmart.dto.form.PropertyTypeOption;
import com.progmasters.moovsmart.dto.form.PropertyAdvertFormData;
import com.progmasters.moovsmart.dto.form.PropertyAdvertInitFormData;
import com.progmasters.moovsmart.dto.form.PropertyCity;
import com.progmasters.moovsmart.dto.form.PropertyEditForm;
import com.progmasters.moovsmart.dto.list.FilterPropertyAdvert;
import com.progmasters.moovsmart.dto.list.PropertyAdvertDetailsData;
import com.progmasters.moovsmart.dto.list.PropertyAdvertListItem;
import com.progmasters.moovsmart.repository.AdvertRepository;
import com.progmasters.moovsmart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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
        Optional<User> user = userRepository.findOneByUserName(userDetails.getUsername());
        if (user.isPresent()) {
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
        );
    }

    public List<PropertyAdvertListItem> listPropertyAdverts() {
        return advertRepository.findPropertyAdvertsByAdvertStatus_FORAPPROVAL().stream()
                .map(PropertyAdvertListItem::new).collect(Collectors.toList());
    }

    //user's property list in profil
    public List<PropertyAdvertListItem> listMyProperties(String userName) {
        return advertRepository.findMyProperties(userName)
                .map(PropertyAdvertListItem::new).collect(Collectors.toList());
    }

    //city list for complex search
    public List<PropertyCity> listCities() {
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

    public List<PropertyAdvertListItem> getFilteredPropertyAdverts(FilterPropertyAdvert filter) {
        Double minPrice;
        Double maxPrice;
        Integer minRooms;
        Integer maxRooms;
        Integer minArea;
        Integer maxArea;

        if (filter.getMinPrice() == null) {
            minPrice = 0.0;
        } else {
            minPrice = filter.getMinPrice();
        }
        if (filter.getMaxPrice() == null) {
            maxPrice = Double.MAX_VALUE;
        } else {
            maxPrice = filter.getMaxPrice();
        }
        if (filter.getMinRooms() == null) {
            minRooms = 0;
        } else {
            minRooms = filter.getMinRooms();
        }
        if (filter.getMaxRooms() == null) {
            maxRooms = Integer.MAX_VALUE;
        } else {
            maxRooms = filter.getMaxRooms();
        }
        if (filter.getMaxArea() == null) {
            maxArea = Integer.MAX_VALUE;
        } else {
            maxArea = filter.getMaxArea();
        }
        if (filter.getMinArea() == null) {
            minArea = 0;
        } else {
            minArea = filter.getMinArea();
        }


        List<PropertyAdvert> filteredPropertyAdverts = advertRepository.findFilteredPropertyAdverts(filter.getCity(), minPrice,
                maxPrice, minArea, maxArea, minRooms, maxRooms,
                filter.getPropertyType(), filter.getPropertyConditionType(), filter.getAdvertStatusType());
        List<PropertyAdvertListItem> filteredList = new ArrayList<>();
        for (PropertyAdvert filteredPropertyAdvert : filteredPropertyAdverts) {
            filteredList.add(new PropertyAdvertListItem(filteredPropertyAdvert));
        }
        return filteredList;
    }

    public boolean updateProperty(PropertyEditForm propertyEditForm, Long id) {
        Optional<PropertyAdvert> propertyAdvertOptional = advertRepository.findById(id);
        if (propertyAdvertOptional.isPresent()) {
            PropertyAdvert propertyAdvert = propertyAdvertOptional.get();
            updateValues(propertyEditForm, propertyAdvert);
            return true;
        } else {
            return false;
        }
    }

    private void updateValues(PropertyEditForm propertyEditForm, PropertyAdvert propertyAdvert) {
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


    //---------------SEARCH-----------------

    public List<PropertyAdvertListItem> listAllProperty() {
        return advertRepository.findAll().stream()
                .map(PropertyAdvertListItem::new).collect(Collectors.toList());
    }


    public List<PropertyAdvertListItem> findAllByPaginator(Integer pageSize, Integer pageIndex) {
        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<PropertyAdvert> queryResult = advertRepository.findAllByPaginator(pageable);
        return getPropertyAdvertsByPage(queryResult);
    }

    List<PropertyAdvertListItem> getPropertyAdvertsByPage(Page<PropertyAdvert> queryResult) {
        if (!queryResult.isEmpty()) {
            List<PropertyAdvertListItem> list = new ArrayList<>();
            for (PropertyAdvert advert : queryResult) {
                list.add(new PropertyAdvertListItem(advert));
            }
            return list;
        } else {
            return null;
        }
    }


}
