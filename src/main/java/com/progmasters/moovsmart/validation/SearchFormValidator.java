package com.progmasters.moovsmart.validation;

import com.progmasters.moovsmart.dto.list.FilterPropertyAdvert;
import com.progmasters.moovsmart.service.PropertyAdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SearchFormValidator implements Validator {

    private PropertyAdvertService propertyAdvertService;

    @Autowired
    public SearchFormValidator(PropertyAdvertService propertyAdvertService) {
        this.propertyAdvertService = propertyAdvertService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(FilterPropertyAdvert.class);
    }

    @Override
    public void validate(Object o, Errors errors) {

        FilterPropertyAdvert filterPropertyAdvert = (FilterPropertyAdvert) o;

        Integer maxArea = filterPropertyAdvert.getMaxArea();
        if(maxArea != null && maxArea < 0) {
            errors.rejectValue("maxArea", "moovsmart.maxArea.invalid");
        }

        Integer minArea = filterPropertyAdvert.getMinArea();
        if(minArea != null && minArea < 0) {
            errors.rejectValue("minArea", "moovsmart.minArea.invalid");
        }

        Double maxPrice = filterPropertyAdvert.getMaxPrice();
        if(maxPrice != null && maxPrice < 0) {
            errors.rejectValue("maxPrice", "moovsmart.maxPrice.invalid");
        }

        Double minPrice = filterPropertyAdvert.getMinPrice();
        if(minPrice != null && minPrice < 0) {
            errors.rejectValue("minPrice", "moovsmart.minPrice.invalid");
        }

        Integer minNumberOfRooms = filterPropertyAdvert.getMinRooms();
        if(minNumberOfRooms != null && minNumberOfRooms < 0) {
            errors.rejectValue("minNumberOfRooms", "moovsmart.minNumberOfRooms.invalid");
        }

        Integer maxNumberOfRooms = filterPropertyAdvert.getMaxRooms();
        if(maxNumberOfRooms != null && maxNumberOfRooms < 0) {
            errors.rejectValue("maxNumberOfRooms", "moovsmart.manNumberOfRooms.invalid");
        }

    }
}
