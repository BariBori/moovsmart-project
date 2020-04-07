package com.progmasters.moovsmart.service.user;

import com.progmasters.moovsmart.domain.PropertyAdvert;
import com.progmasters.moovsmart.domain.user.RegistrationToken;
import com.progmasters.moovsmart.domain.user.User;
import com.progmasters.moovsmart.domain.user.UserIdentifier;
import com.progmasters.moovsmart.domain.user.UserRole;
import com.progmasters.moovsmart.dto.list.PropertyAdvertListItem;
import com.progmasters.moovsmart.dto.user.UserForm;
import com.progmasters.moovsmart.repository.AdvertRepository;
import com.progmasters.moovsmart.repository.RegistrationTokenRepository;
import com.progmasters.moovsmart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class UserService {
    private UserRepository userRepository;
    private RegistrationTokenRepository registrationTokenRepository;
    private UserActivationService userActivationService;
    private PasswordEncoder encoder;
    private AdvertRepository advertRepository;

    @Autowired
    public UserService(
            UserRepository userRepository,
            RegistrationTokenRepository registrationTokenRepository,
            UserActivationService userActivationService, PasswordEncoder encoder,
            AdvertRepository advertRepository) {

        this.userRepository = userRepository;
        this.registrationTokenRepository = registrationTokenRepository;
        this.userActivationService = userActivationService;
        this.encoder = encoder;
        this.advertRepository = advertRepository;

    }

    public boolean isEmailTaken(String emailAddress) {
        return userRepository.findOneByEmail(emailAddress).isPresent();
    }

    public boolean isUserNameTaken(String userName) {
        return userRepository.findOneByUserName(userName).isPresent();
    }

    public User registerUser(UserForm userDto) {
        RegistrationToken token = registrationTokenRepository.save(RegistrationToken.forUser(
                userRepository.save(new User(
                        userDto.getEmail(),
                        userDto.getUserName(),
                        encoder.encode(userDto.getPassword()),
                        UserRole.ROLE_USER
                ))
        ));
        userActivationService.sendActivationEmail(token);
        return token.getUser();
    }

    public List<PropertyAdvertListItem> addFavouriteAdvert(UserIdentifier user, Long advertId) {
        PropertyAdvert advert = advertRepository.getOne(advertId);
        return userRepository
                .save(userForDetails(user).addSavedAdvert(advert)).getSavedAdverts().stream()
                .map(PropertyAdvertListItem::new)
                .collect(Collectors.toList());
    }

    public List<PropertyAdvertListItem> removeFavouriteAdvert(UserIdentifier user, Long advertId) {
        PropertyAdvert advert = advertRepository.getOne(advertId);
        return userRepository
                .save(userForDetails(user).removeSavedAdvert(advert)).getSavedAdverts().stream()
                .map(PropertyAdvertListItem::new)
                .collect(Collectors.toList());
    }


    public User userForDetails(UserIdentifier userDetails) {
        return userRepository.getOne(userDetails.getId());
    }

    public List<PropertyAdvertListItem> getFavouriteAdverts(UserIdentifier userIdentifier) {
        return userForDetails(userIdentifier).getSavedAdverts().stream()
                .map(PropertyAdvertListItem::new)
                .collect(Collectors.toList());
    }
}
