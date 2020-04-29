package com.progmasters.moovsmart.controller;

import com.progmasters.moovsmart.domain.Bid;
import com.progmasters.moovsmart.domain.PropertyAdvert;
import com.progmasters.moovsmart.domain.search.PropertySpecificationBuilder;
import com.progmasters.moovsmart.dto.BidListItem;
import com.progmasters.moovsmart.dto.form.*;
import com.progmasters.moovsmart.dto.list.FilterPropertyAdvert;
import com.progmasters.moovsmart.dto.list.PageList;
import com.progmasters.moovsmart.dto.list.PropertyAdvertDetailsData;
import com.progmasters.moovsmart.dto.list.PropertyAdvertListItem;
import com.progmasters.moovsmart.service.BidService;
import com.progmasters.moovsmart.service.PropertyAdvertService;
import com.progmasters.moovsmart.service.user.UserService;
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

//import com.progmasters.moovsmart.domain.user.UserDetailsImpl;

@RestController
@RequestMapping("/api/properties")
public class PropertyAdvertController {

    private static final Logger logger = LoggerFactory.getLogger(PropertyAdvertController.class);

    private PropertyAdvertService propertyAdvertService;
    private PropertyAdvertValidator propertyAdvertValidator;
    private UserDetailsFromSecurityContext userDetails;
    private UserService userService;
    private BidService bidService;

    @Autowired
    public PropertyAdvertController(
            PropertyAdvertService propertyAdvertService,
            PropertyAdvertValidator propertyAdvertValidator,
            UserDetailsFromSecurityContext userDetails,
            UserService userService, BidService bidService) {
        this.propertyAdvertService = propertyAdvertService;
        this.propertyAdvertValidator = propertyAdvertValidator;
        this.userDetails = userDetails;
        this.userService = userService;
        this.bidService = bidService;
    }

    @InitBinder("propertyAdvertFormData")
    public void bind(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(propertyAdvertValidator);
    }

    @PostMapping("/fav/{advertId}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<PropertyAdvertListItem>> saveFavouriteAdvert(@PathVariable Long advertId) {
        return ResponseEntity.ok(
                userService.addFavouriteAdvert(userDetails.get(), advertId)
        );
    }


    @PostMapping("/bid/{advertId}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Bid> saveBid(@PathVariable Long advertId, @RequestBody BidFormData bidFormData) {
        return ResponseEntity.ok(
                bidService.saveBid(bidFormData, userDetails.get(), advertId)
        );
    }


    @GetMapping("/fav")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<PropertyAdvertListItem>> getFavouriteAdverts() {
        return ResponseEntity.ok(
                userService.getFavouriteAdverts(userDetails.get())
        );
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

    @PostMapping("/page")
    public ResponseEntity<List<PropertyAdvertListItem>> getPaginatorPropertyAdverts(@RequestBody PageList pageList) {
        return new ResponseEntity<>(propertyAdvertService.findAllByPaginator(pageList.getPageSize(), pageList.getPageIndex()), HttpStatus.OK);
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
    public ResponseEntity<List<PropertyAdvertListItem>> getMyProperties(@PathVariable String userName) {
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

    @DeleteMapping("/fav/{id}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<PropertyAdvertListItem>> getFavouriteAdverts(@PathVariable Long id) {
        return ResponseEntity.ok(
                userService.removeFavouriteAdvert(userDetails.get(), id)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyAdvertDetailsData> getAdvertDetails(@PathVariable Long id) {
        return new ResponseEntity<>(propertyAdvertService.getPropertyAdvertDetails(id), HttpStatus.OK);
    }


    //--------------SEARCH-----------------

    @GetMapping("/propertySearch")
    @ResponseBody
    public ResponseEntity<List<PropertyAdvertListItem>> search(@RequestParam(value = "search") String search) {
        PropertySpecificationBuilder builder = new PropertySpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),", Pattern.UNICODE_CHARACTER_CLASS);
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        Specification<PropertyAdvert> spec = builder.build();
        return new ResponseEntity<>(propertyAdvertService.listAllProperty(), HttpStatus.OK);
    }


}
