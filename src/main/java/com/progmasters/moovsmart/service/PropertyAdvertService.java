package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.domain.*;
import com.progmasters.moovsmart.dto.*;
import com.progmasters.moovsmart.repository.AdvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
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
                Arrays.stream(PropertyConstructionType.values()).map(ConstructionTypeOption::new).collect(Collectors.toList()),
                Arrays.stream(ParkingType.values()).map(ParkingTypeOption::new).collect(Collectors.toList())
                );
    }
}