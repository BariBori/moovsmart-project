package com.progmasters.moovsmart.validation;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.progmasters.moovsmart.dto.form.BidFormData;
import com.progmasters.moovsmart.dto.list.PropertyAdvertDetailsData;
import com.progmasters.moovsmart.service.BidService;
import com.progmasters.moovsmart.service.PropertyAdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BidFormValidator implements Validator {

    private BidService bidService;
    private PropertyAdvertService propertyAdvertService;

    @Autowired
    public BidFormValidator(BidService bidService, PropertyAdvertService propertyAdvertService){
        this.bidService = bidService;
        this.propertyAdvertService = propertyAdvertService;

    }

    @Override
    public boolean supports(Class<?> aClass){ return aClass.equals(BidFormData.class);
    }

    @Override
    public void validate(Object o, Errors errors){
        BidFormData bidFormData = (BidFormData) o;

        Double amountOfBid = bidFormData.getAmountOfBid();
        Long propertyId = bidFormData.getPropertyAdvertId();

        Double actualPrice = this.propertyAdvertService.getPropertyAdvertDetails(propertyId).getActualPrice();

        if(amountOfBid == null || amountOfBid < actualPrice){
            errors.rejectValue("amountOfBid", "moovsmart.amountOfBid.invalid");
        }
    }
}
