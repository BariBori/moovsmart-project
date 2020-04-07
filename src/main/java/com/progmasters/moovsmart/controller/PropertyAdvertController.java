package com.progmasters.moovsmart.controller;

import com.progmasters.moovsmart.domain.PropertyAdvert;
import com.progmasters.moovsmart.domain.search.PropertySpecificationBuilder;
//import com.progmasters.moovsmart.domain.user.UserDetailsImpl;
import com.progmasters.moovsmart.dto.form.PropertyAdvertFormData;
import com.progmasters.moovsmart.dto.form.PropertyAdvertInitFormData;
import com.progmasters.moovsmart.dto.form.PropertyCity;
import com.progmasters.moovsmart.dto.form.PropertyEditForm;
import com.progmasters.moovsmart.dto.list.FilterPropertyAdvert;
import com.progmasters.moovsmart.dto.list.PropertyAdvertDetailsData;
import com.progmasters.moovsmart.dto.list.PropertyAdvertListItem;
import com.progmasters.moovsmart.service.PropertyAdvertService;
import com.progmasters.moovsmart.utils.UserDetailsFromSecurityContext;
import com.progmasters.moovsmart.validation.PropertyAdvertValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
    private UserDetailsFromSecurityContext userDetails;

    @Autowired
    public PropertyAdvertController(
            PropertyAdvertService propertyAdvertService,
            PropertyAdvertValidator propertyAdvertValidator,
            UserDetailsFromSecurityContext userDetails
    ) {
        this.propertyAdvertService = propertyAdvertService;
        this.propertyAdvertValidator = propertyAdvertValidator;
        this.userDetails = userDetails;
    }

    @InitBinder("propertyAdvertFormData")
    public void bind(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(propertyAdvertValidator);
    }

    @PostMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Void> createPropertyAdvert(@RequestBody @Valid PropertyAdvertFormData propertyAdvertFormData) {
        logger.info("The advert is created");
        propertyAdvertService.saveAdvert(propertyAdvertFormData, userDetails.get());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/search")
    public ResponseEntity<List<PropertyAdvertListItem>> getFilteredPropertyAdverts(@RequestBody FilterPropertyAdvert filterPropertyAdvert) {
        List<PropertyAdvertListItem> filteredPropertyAdverts = propertyAdvertService.getFilteredPropertyAdverts(filterPropertyAdvert);
        return new ResponseEntity<>(filteredPropertyAdverts, HttpStatus.OK);
    }

  @GetMapping("\"/\" + {pageSize} + \"/\" + {pageIndex}")
    public ResponseEntity<List<PropertyAdvertListItem>> getPaginatorPropertyAdverts(@PathVariable Integer pageSize, @PathVariable Integer pageIndex)
   {
        return new ResponseEntity<>(propertyAdvertService.findAllByPaginator(pageSize, pageIndex), HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<PropertyAdvertListItem>> getPropertyAdverts() {
        return new ResponseEntity<>(propertyAdvertService.listPropertyAdverts(), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<PropertyAdvertDetailsData> updateProperty(@Valid @RequestBody PropertyEditForm propertyEditForm, @PathVariable Long id) {
        boolean isUpdated = propertyAdvertService.updateProperty(propertyEditForm, id);
        ResponseEntity<PropertyAdvertDetailsData> result;
        if (!isUpdated) {
            result = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            result = new ResponseEntity<>(HttpStatus.OK);
        }
        return result;
    }


    @GetMapping("/formData")
    public ResponseEntity<PropertyAdvertInitFormData> getFormInitData() {
        return new ResponseEntity<>(propertyAdvertService.createPropertyAdvertFormInitData(), HttpStatus.OK);
    }


    //user's property list in profil
    @GetMapping("/myProperties/{userName}")
    public ResponseEntity<List<PropertyAdvertListItem>> getMyProperties(@PathVariable String userName){
        return new ResponseEntity<>(propertyAdvertService.listMyProperties(userName), HttpStatus.OK);
    }

    //city list for complex search
    @GetMapping("/cities")
    public ResponseEntity<List<PropertyCity>> getCities() {
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
        return new ResponseEntity<>(propertyAdvertService.listAllProperty(), HttpStatus.OK);
    }



}
