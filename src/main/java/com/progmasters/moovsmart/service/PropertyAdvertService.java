package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.domain.PropertyAdvert;
import com.progmasters.moovsmart.dto.PropertyAdvertFormData;
import com.progmasters.moovsmart.repository.AdvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
