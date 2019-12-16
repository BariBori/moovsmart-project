package com.progmasters.moovsmart.controller;

import com.progmasters.moovsmart.dto.PropertyDetails;
import com.progmasters.moovsmart.dto.PropertyForm;
import com.progmasters.moovsmart.dto.PropertyListItem;
import com.progmasters.moovsmart.service.PropertyService;
import com.progmasters.moovsmart.validation.PropertyFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private PropertyService propertyService;
    private PropertyFormValidator propertyFormValidator;

    @Autowired
    public PropertyController(PropertyService propertyService, PropertyFormValidator propertyFormValidator) {
        this.propertyService = propertyService;
        this.propertyFormValidator = propertyFormValidator;
    }

    @InitBinder("propertyDetails")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(propertyFormValidator);
    }

    @GetMapping
    public ResponseEntity<List<PropertyListItem>> getAllProperties() {
        return new ResponseEntity<>(propertyService.getProperties(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyDetails> getPropertyDetails(@PathVariable("id") Long id) {
        return new ResponseEntity<>(propertyService.getPropertyDetails(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createProperty(@RequestBody @Valid PropertyForm propertyForm) {
        propertyService.createProperty(propertyForm);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
