package com.progmasters.moovsmart.validation;

import com.progmasters.moovsmart.domain.Image;
import com.progmasters.moovsmart.domain.PropertyAdvert;
import com.progmasters.moovsmart.dto.PropertyAdvertFormData;
import com.progmasters.moovsmart.service.PropertyAdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.List;

@Component
public class PropertyAdvertValidator implements Validator {

    private PropertyAdvertService propertyAdvertService;

    @Autowired
    public PropertyAdvertValidator(PropertyAdvertService propertyAdvertService) {
        this.propertyAdvertService = propertyAdvertService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(PropertyAdvertFormData.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        PropertyAdvertFormData propertyAdvertFormData = (PropertyAdvertFormData) o;
        Integer price = propertyAdvertFormData.getPrice();
        if(price <= 0) {
            errors.rejectValue("price", "moovsmart.price.invalid");
        }
        List<Image> listOfImages = propertyAdvertFormData.getListOfImages();
        if (listOfImages == null) {
            errors.rejectValue("listOfImages", "moovsmart.listOfImages.invalid");
        }
        String title = propertyAdvertFormData.getTitle();
        if (title.length() < 10 || title.length() > 50) {
            errors.rejectValue("title", "moovsmart.title.invalid");
        }
        Integer area = propertyAdvertFormData.getArea();
        if (area <= 0) {
            errors.rejectValue("area", "moovsmart.area.invalid");
        }
        Integer numberOfRooms = propertyAdvertFormData.getNumberOfRooms();
        if (numberOfRooms <= 0) {
            errors.rejectValue("numberOfRooms", "moovsmart.numberOfRooms.invalid" );
        }
        String description = propertyAdvertFormData.getDescription();
        if(description.length() < 20 ||description.length() > 600) {
            errors.rejectValue("description", "moovsmart.description.invalid");
        }
    }
}
