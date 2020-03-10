package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.domain.Advert;
import com.progmasters.moovsmart.dto.AdvertFormData;
import com.progmasters.moovsmart.repository.AdvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdvertService {

    private AdvertRepository advertRepository;

    @Autowired
    public AdvertService(AdvertRepository advertRepository) {
        this.advertRepository = advertRepository;
    }

    public Advert saveAdvert(AdvertFormData advertFormData) {
        Advert advert = new Advert(advertFormData);
        return this.advertRepository.save(advert);
    }
}
