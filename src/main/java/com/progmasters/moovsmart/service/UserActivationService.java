package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.domain.RegistrationToken;
import com.progmasters.moovsmart.domain.User;
import com.progmasters.moovsmart.repository.RegistrationTokenRepository;
import com.progmasters.moovsmart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
public class UserActivationService {
    private RegistrationTokenRepository registrationTokenRepository;
    private UserRepository userRepository;

    @Autowired
    public UserActivationService(RegistrationTokenRepository registrationTokenRepository, UserRepository userRepository) {
        this.registrationTokenRepository = registrationTokenRepository;
        this.userRepository = userRepository;
    }

    public User activateUserByTokenId(UUID uuid) {
        User user = registrationTokenRepository.findByUuid(uuid)
                .map(RegistrationToken::getUser)
                .orElseThrow(() ->
                        new EntityNotFoundException("no User found for this Token Id"));
        user.confirmRegistration();
        return userRepository.save(user);
    }
}
