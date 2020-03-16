package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.domain.RegistrationToken;
import com.progmasters.moovsmart.domain.User;
import com.progmasters.moovsmart.domain.UserRole;
import com.progmasters.moovsmart.dto.UserForm;
import com.progmasters.moovsmart.repository.RegistrationTokenRepository;
import com.progmasters.moovsmart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserService {
    private UserRepository userRepository;
    private RegistrationTokenRepository registrationTokenRepository;
    private PersonalDetailsService personalDetailsService;
    private UserActivationService userActivationService;
    private PasswordEncoder encoder;

    @Autowired
    public UserService(
            UserRepository userRepository,
            RegistrationTokenRepository registrationTokenRepository,
            PersonalDetailsService personalDetailsService,
            UserActivationService userActivationService, PasswordEncoder encoder
    ) {
        this.userRepository = userRepository;
        this.registrationTokenRepository = registrationTokenRepository;
        this.personalDetailsService = personalDetailsService;
        this.userActivationService = userActivationService;
        this.encoder = encoder;
    }

    public boolean isEmailTaken(String emailAddress) {
        return userRepository.findByEmail(emailAddress).isPresent();
    }

    public boolean isUserNameTaken(String userName) {
        return userRepository.findByUserName(userName).isPresent();
    }

    public User registerUser(UserForm userDto) {
        RegistrationToken token = registrationTokenRepository.save(RegistrationToken.forUser(
                userRepository.save(new User(
                        userDto.getEmail(),
                        userDto.getUserName(),
                        encoder.encode(userDto.getPassword()),
                        personalDetailsService.save(userDto.getPersonalDetails()),
                        UserRole.ROLE_USER
                ))
        ));
        userActivationService.sendActivationEmail(token);
        return token.getUser();
    }
}
