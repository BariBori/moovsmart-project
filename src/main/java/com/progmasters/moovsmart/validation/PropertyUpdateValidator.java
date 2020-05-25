package com.progmasters.moovsmart.validation;

import com.progmasters.moovsmart.dto.form.PropertyEditForm;
import com.progmasters.moovsmart.service.PropertyAdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

@Component
public class PropertyUpdateValidator implements Validator {

    private PropertyAdvertService propertyAdvertService;

    @Autowired
    public PropertyUpdateValidator(PropertyAdvertService propertyAdvertService) {
        this.propertyAdvertService = propertyAdvertService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(PropertyEditForm.class);
    }

    @Override
    public void validate(Object o, Errors errors) {

        PropertyEditForm propertyEditForm = (PropertyEditForm) o;
        Double price = propertyEditForm.getPrice();
        if (price == null || price <= 0 || price > 1000) {
            errors.rejectValue("price", "moovsmart.price.invalid");
        }

        String title = propertyEditForm.getTitle();
        if (title.length() < 10 || title.length() > 50) {
            errors.rejectValue("title", "moovsmart.title.invalid");
        }

        Integer area = propertyEditForm.getArea();
        if (area == null || area <= 10 || area > 1000) {
            errors.rejectValue("area", "moovsmart.area.invalid");
        }

        Integer numberOfRooms = propertyEditForm.getNumberOfRooms();
        if (numberOfRooms == null || numberOfRooms < 1 || numberOfRooms > 30) {
            errors.rejectValue("numberOfRooms", "moovsmart.numberOfRooms.invalid");
        }

        String description = propertyEditForm.getDescription();
        if (description.length() < 20 || description.length() > 600) {
            errors.rejectValue("description", "moovsmart.description.invalid");
        }

        LocalDateTime startOfAuction = propertyEditForm.getStartOfAuction();
        LocalDateTime endOfAuction = propertyEditForm.getEndOfAuction();
        LocalDateTime today = LocalDateTime.now();
        if((startOfAuction !=null && endOfAuction!=null) && endOfAuction.isBefore(startOfAuction)){
            errors.rejectValue("endOfAuction", "moovsmart.endOfAuction.invalid");
        }

        if(startOfAuction!=null && startOfAuction.isBefore(today)){
            errors.rejectValue("startOfAuction", "moovsmart.startOfAuction.before.today");
        }

        if(endOfAuction!=null && endOfAuction.isBefore(today)){
            errors.rejectValue("endOfAuction", "moovsmart.endOfAuction.before.today");
        }

        if(startOfAuction !=null && endOfAuction == null){
            errors.rejectValue("endOfAuction", "moovsmart.endOfAuction.missing");
        }

        if(startOfAuction ==null && endOfAuction != null){
            errors.rejectValue("startOfAuction", "moovsmart.startOfAuction.missing");
        }

        if(startOfAuction !=null && startOfAuction.getYear()>today.getYear()+1){
            errors.rejectValue("startOfAuction", "moovsmart.startOfAuction.tooFar");
        }

        if(endOfAuction !=null && endOfAuction.getYear()>today.getYear()+1){
            errors.rejectValue("endOfAuction", "moovsmart.endOfAuction.tooFar");
        }

    }
}
