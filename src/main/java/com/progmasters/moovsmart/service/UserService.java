package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.domain.RegistrationToken;
import com.progmasters.moovsmart.domain.User;
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
    private PasswordEncoder encoder;

    @Autowired
    public UserService(
            UserRepository userRepository,
            RegistrationTokenRepository registrationTokenRepository,
            PersonalDetailsService personalDetailsService,
            PasswordEncoder encoder
    ) {
        this.userRepository = userRepository;
        this.registrationTokenRepository = registrationTokenRepository;
        this.personalDetailsService = personalDetailsService;
        this.encoder = encoder;
    }

    public User registerUser(UserForm userDto) {
        return registrationTokenRepository.save(RegistrationToken.forUser(
                userRepository.save(new User(
                        userDto.getEmail(),
                        encoder.encode(userDto.getPassword()),
                        personalDetailsService.save(userDto.getPersonalDetails())
                ))
        ))
                .getUser();
    }
}
