package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.domain.Property;
import com.progmasters.moovsmart.dto.PropertyDetails;
import com.progmasters.moovsmart.dto.PropertyForm;
import com.progmasters.moovsmart.dto.PropertyListItem;
import com.progmasters.moovsmart.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PropertyService {

    private PropertyRepository propertyRepository;

    @Autowired
    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public List<PropertyListItem> getProperties() {
        List<Property> properties = propertyRepository.findAll();
        return properties.stream().map(PropertyListItem::new).collect(Collectors.toList());
    }

    public PropertyDetails getPropertyDetails(Long id) {
        Property property = propertyRepository.getOne(id);
        return new PropertyDetails(property);
    }

    public void createProperty(PropertyForm propertyForm) {
        propertyRepository.save(new Property(propertyForm));
    }
}
