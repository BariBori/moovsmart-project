package com.progmasters.moovsmart.validation;

import com.progmasters.moovsmart.dto.form.PropertyAdvertFormData;
import com.progmasters.moovsmart.service.PropertyAdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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
        Double price = propertyAdvertFormData.getPrice();
        if (price == null || price <= 0) {
            errors.rejectValue("price", "moovsmart.price.invalid");
        }
        // List<Image> listOfImages = propertyAdvertFormData.getListOfImages();
        // if (listOfImages == null) {
        //     errors.rejectValue("listOfImages", "moovsmart.listOfImages.invalid");
        // }
        //TODO
        String title = propertyAdvertFormData.getTitle();
        if (title.length() < 10 || title.length() > 50) {
            errors.rejectValue("title", "moovsmart.title.invalid");
        }
        Integer area = propertyAdvertFormData.getArea();
        if (area == null || area <= 0) {
            errors.rejectValue("area", "moovsmart.area.invalid");
        }
        Integer numberOfRooms = propertyAdvertFormData.getNumberOfRooms();
        if (numberOfRooms == null || numberOfRooms <= 0) {
            errors.rejectValue("numberOfRooms", "moovsmart.numberOfRooms.invalid");
        }
        String description = propertyAdvertFormData.getDescription();
        if (description.length() < 20 || description.length() > 600) {
            errors.rejectValue("description", "moovsmart.description.invalid");
        }
    }
}
