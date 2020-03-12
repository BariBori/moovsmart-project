package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.domain.*;
import com.progmasters.moovsmart.dto.*;
import com.progmasters.moovsmart.repository.AdvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PropertyAdvertService {

    private AdvertRepository advertRepository;

    @Autowired
    public PropertyAdvertService(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    public PropertyAdvert saveAdvert(PropertyAdvertFormData propertyAdvertFormData) {
        PropertyAdvert propertyAdvert = new PropertyAdvert(propertyAdvertFormData);
        return this.advertRepository.save(propertyAdvert);
    }

    public PropertyAdvertInitFormData createPropertyAdvertFormInitData() {
        return new PropertyAdvertInitFormData(
                Arrays.stream(PropertyType.values()).map(PropertyTypeOption::new).collect(Collectors.toList()),
                Arrays.stream(PropertyConditionType.values()).map(PropertyConditionOption::new).collect(Collectors.toList()),
                Arrays.stream(ParkingType.values()).map(ParkingTypeOption::new).collect(Collectors.toList())
                );
    }

    public List<PropertyAdvertListItem> listPropertyAdverts() {
        return advertRepository.findByOrderByTimeOfActivationDesc().stream()
                .map(propertyAdvert -> new PropertyAdvertListItem(propertyAdvert)).collect(Collectors.toList());
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


}
