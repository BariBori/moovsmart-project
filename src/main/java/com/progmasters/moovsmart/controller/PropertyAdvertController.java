package com.progmasters.moovsmart.controller;

import com.progmasters.moovsmart.dto.PropertyAdvertFormData;
import com.progmasters.moovsmart.dto.PropertyAdvertInitFormData;
import com.progmasters.moovsmart.service.PropertyAdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/propertyadvert-form")
public class PropertyAdvertController {

    private PropertyAdvertService propertyAdvertService;

    @Autowired
    public PropertyAdvertController(PropertyAdvertService propertyAdvertService) {
        this.propertyAdvertService = propertyAdvertService;
    }

    @PostMapping
    public ResponseEntity<Void> createPropertyAdvert(@RequestBody PropertyAdvertFormData propertyAdvertFormData) {
        propertyAdvertService.saveAdvert(propertyAdvertFormData);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/formData")
    public ResponseEntity<PropertyAdvertInitFormData> getFormInitData() {
        return new ResponseEntity<>(propertyAdvertService.createPropertyAdvertFormInitData(), HttpStatus.OK);
    }

}
