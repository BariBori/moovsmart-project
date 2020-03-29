package com.progmasters.moovsmart.controller;

import com.progmasters.moovsmart.domain.PropertyAdvert;
import com.progmasters.moovsmart.domain.PropertyConditionType;
import com.progmasters.moovsmart.domain.PropertyType;
import com.progmasters.moovsmart.domain.search.PropertySpecificationBuilder;
import com.progmasters.moovsmart.domain.user.User;
import com.progmasters.moovsmart.dto.*;
import com.progmasters.moovsmart.domain.user.UserDetailsImpl;
import com.progmasters.moovsmart.dto.*;
import com.progmasters.moovsmart.service.PropertyAdvertService;
import com.progmasters.moovsmart.validation.PropertyAdvertValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/properties")
public class PropertyAdvertController {

    private static final Logger logger = LoggerFactory.getLogger(PropertyAdvertController.class);

    private PropertyAdvertService propertyAdvertService;
    private PropertyAdvertValidator propertyAdvertValidator;

    @Autowired
    public PropertyAdvertController(PropertyAdvertService propertyAdvertService, PropertyAdvertValidator propertyAdvertValidator) {
        this.propertyAdvertService = propertyAdvertService;
        this.propertyAdvertValidator = propertyAdvertValidator;
    }

    @InitBinder("propertyAdvertFormData")
    public void bind(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(propertyAdvertValidator);
    }

    @PostMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Void> createPropertyAdvert(@RequestBody @Valid PropertyAdvertFormData propertyAdvertFormData) {
        logger.info("The advert is created");
        UserDetailsImpl userDetails =
                (UserDetailsImpl) SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();
        propertyAdvertService.saveAdvert(propertyAdvertFormData, userDetails);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

//    @PostMapping
//    public List<PropertyAdvertListItem> getFilteredPropertyAdverts(@RequestBody FilterPropertyAdvert filterPropertyAdvert) {
//        return propertyAdvertService.getFilteredPropertyAdverts(filterPropertyAdvert);
//    }


    @PutMapping("/{id}")
    public ResponseEntity<PropertyAdvertDetailsData> updateProperty(@Valid @RequestBody PropertyEditForm propertyEditForm, @PathVariable Long id) {
        Boolean isUpdated = propertyAdvertService.updateProperty(propertyEditForm, id);
        ResponseEntity<PropertyAdvertDetailsData> result;
        if (!isUpdated) {
            result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
           // result = new ResponseEntity<>(new PropertyAdvertDetailsData(updatedProperty), HttpStatus.OK);
            result = new ResponseEntity<>(HttpStatus.OK);
        }
        return result;
    }


    @GetMapping("/formData")
    public ResponseEntity<PropertyAdvertInitFormData> getFormInitData() {
        return new ResponseEntity<>(propertyAdvertService.createPropertyAdvertFormInitData(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PropertyAdvertListItem>> getPropertyAdverts() {
        return new ResponseEntity<>(propertyAdvertService.listPropertyAdverts(), HttpStatus.OK);
    }

    @GetMapping("/cities")
    public ResponseEntity<List<PropertyCity>> getCities(){
        return new ResponseEntity<>(propertyAdvertService.listCities(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<PropertyAdvertListItem>> archivePropertyAdvert(@PathVariable Long id) {
        boolean isDeleteSuccessful = propertyAdvertService.archivePropertyAdvert(id);
        ResponseEntity<List<PropertyAdvertListItem>> result;
        if (isDeleteSuccessful) {
            result = new ResponseEntity<>(propertyAdvertService.listPropertyAdverts(), HttpStatus.OK);
        } else {
            result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return result;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyAdvertDetailsData> getAdvertDetails(@PathVariable Long id) {
        return new ResponseEntity<>(propertyAdvertService.getPropertyAdvertDetails(id), HttpStatus.OK);
    }


    //--------------SEARCH-----------------

    @GetMapping("/propertySearch")
    @ResponseBody
    public ResponseEntity<List<PropertyAdvertListItem>> search(@RequestParam ( value = "search") String search){
        PropertySpecificationBuilder builder = new PropertySpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(search + ",");
        while(matcher.find()){
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        Specification<PropertyAdvert> spec = builder.build();
        return new ResponseEntity<>(propertyAdvertService.listAllProperty(spec), HttpStatus.OK);
    }



}
