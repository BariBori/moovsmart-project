package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.domain.Bid;
import com.progmasters.moovsmart.domain.PropertyAdvert;
import com.progmasters.moovsmart.domain.user.User;
import com.progmasters.moovsmart.domain.user.UserIdentifier;
import com.progmasters.moovsmart.dto.form.BidFormData;
import com.progmasters.moovsmart.repository.AdvertRepository;
import com.progmasters.moovsmart.repository.BidRepository;
import com.progmasters.moovsmart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BidService {

    private BidRepository bidRepository;
    private AdvertRepository advertRepository;
    private UserRepository userRepository;

    @Autowired
    public BidService(BidRepository bidRepository, AdvertRepository advertRepository, UserRepository userRepository) {
        this.bidRepository = bidRepository;
        this.advertRepository = advertRepository;
        this.userRepository = userRepository;
    }

    public Bid saveBid(BidFormData bidFormData, UserIdentifier userIdentifier, Long advertId){
        Optional<User> user = userRepository.findOneByUserName(userIdentifier.getUsername());
        Optional<PropertyAdvert> propertyAdvert = advertRepository.findOneById(advertId);
        Bid bid;

        if (user.isPresent() && propertyAdvert.isPresent()) {
            User chosenUser = user.get();
            PropertyAdvert chosenAdvert = propertyAdvert.get();
            bid = new Bid(chosenAdvert, chosenUser, bidFormData);
            this.bidRepository.save(bid);
        } else{
            bid = null;
        }

        return bid;
    }
}
