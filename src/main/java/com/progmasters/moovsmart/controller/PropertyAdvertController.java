package com.progmasters.moovsmart.controller;

import com.progmasters.moovsmart.domain.Bid;
import com.progmasters.moovsmart.dto.form.*;
import com.progmasters.moovsmart.dto.list.*;
import com.progmasters.moovsmart.service.BidService;
import com.progmasters.moovsmart.service.PropertyAdvertService;
import com.progmasters.moovsmart.service.user.UserService;
import com.progmasters.moovsmart.utils.UserDetailsFromSecurityContext;
import com.progmasters.moovsmart.validation.PropertyAdvertValidator;
import com.sun.xml.bind.v2.schemagen.xmlschema.Any;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/fav")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<PropertyAdvertListItem>> getFavouriteAdverts() {
        return ResponseEntity.ok(
                userService.getFavouriteAdverts(userDetails.get())
        );
    }

    @PostMapping("/property-details/{advertId}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Void> saveBid(@PathVariable Long advertId, @RequestBody BidFormData bidFormData) {
        logger.info("The bid is created");
        propertyAdvertService.updatePropertyActualPrice(bidFormData,advertId);
        bidService.saveBid(bidFormData, userDetails.get(), propertyAdvertService.getPropertyAdvertDetails(advertId).getId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/property-details/bids/{advertId}")
    public ResponseEntity<List<BidListItem>> getPropertyBids(@PathVariable Long advertId){
        return new ResponseEntity<>(bidService.listBidsByPropertyId(advertId), HttpStatus.OK);
    }

    @GetMapping("/property-details/bidder/{advertId}")
    public ResponseEntity<Long> getNumberOfBidUser(@PathVariable Long advertId) {
        return new ResponseEntity<>(bidService.getBidUserNumber(advertId), HttpStatus.OK);
    }

    @GetMapping("/property-details/lastBid/{advertId}")
    public ResponseEntity<Double> getLastBidAmount(@PathVariable Long advertId) {
        return new ResponseEntity<>(bidService.getLastBidAmount(advertId), HttpStatus.OK);
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

    //user's bids in profil
    @GetMapping("/property-details/myBids/{userName}")
    public ResponseEntity<List<MyBidList>> getMyBidProperties(@PathVariable String userName){
        return new ResponseEntity<>(bidService.listMyBidProperties(userName), HttpStatus.OK);
    }

    @GetMapping("/property-details/myBids/{advertId}/{userName}")
    public ResponseEntity<List<MyBidList>> getMyBidsByProperties(@PathVariable Long advertId, @PathVariable String userName){
        return new ResponseEntity<>(bidService.listMyBidsByProperty(advertId, userName), HttpStatus.OK);
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





}
