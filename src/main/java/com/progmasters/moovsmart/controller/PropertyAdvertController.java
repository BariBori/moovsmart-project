package com.progmasters.moovsmart.controller;

import com.progmasters.moovsmart.dto.PropertyAdvertFormData;
import com.progmasters.moovsmart.dto.PropertyAdvertInitFormData;
import com.progmasters.moovsmart.dto.PropertyAdvertListItem;
import com.progmasters.moovsmart.service.PropertyAdvertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyAdvertController {

    private static final Logger logger = LoggerFactory.getLogger(PropertyAdvertController.class);

    private PropertyAdvertService propertyAdvertService;

    @Autowired
    public PropertyAdvertController(PropertyAdvertService propertyAdvertService) {
        this.propertyAdvertService = propertyAdvertService;
    }

    @PostMapping
    public ResponseEntity<Void> createPropertyAdvert(@RequestBody PropertyAdvertFormData propertyAdvertFormData) {
        propertyAdvertService.saveAdvert(propertyAdvertFormData);
        logger.info("The advert is created");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/formData")
    public ResponseEntity<PropertyAdvertInitFormData> getFormInitData() {
        return new ResponseEntity<>(propertyAdvertService.createPropertyAdvertFormInitData(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PropertyAdvertListItem>> getPropertyAdverts() {
        return new ResponseEntity<>(propertyAdvertService.listPropertyAdverts(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
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



}
