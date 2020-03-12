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

import javax.persistence.EntityNotFoundException;
import java.util.UUID;


@Service
@Transactional
public class UserService {
    private UserRepository userRepository;
    private RegistrationTokenRepository registrationTokenRepository;
    private PersonalDetailsService service;
    private PasswordEncoder encoder;

    @Autowired
    public UserService(
            UserRepository userRepository,
            RegistrationTokenRepository registrationTokenRepository,
            PersonalDetailsService service,
            PasswordEncoder encoder
    ) {
        this.userRepository = userRepository;
        this.registrationTokenRepository = registrationTokenRepository;
        this.service = service;
        this.encoder = encoder;
    }

    public User registerUser(UserForm userDto) {
        return userRepository.save(
                new User(
                        userDto.getEmail(),
                        encoder.encode(userDto.getPassword()),
                        service.save(userDto.getPersonalDetails())
                )
        );
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
