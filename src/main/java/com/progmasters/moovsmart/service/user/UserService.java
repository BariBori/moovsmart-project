package com.progmasters.moovsmart.service.user;

import com.progmasters.moovsmart.domain.user.RegistrationToken;
import com.progmasters.moovsmart.domain.user.User;
import com.progmasters.moovsmart.domain.user.UserIdentifier;
import com.progmasters.moovsmart.domain.user.UserRole;
import com.progmasters.moovsmart.dto.user.UserForm;
import com.progmasters.moovsmart.repository.RegistrationTokenRepository;
import com.progmasters.moovsmart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@Service
@Transactional
public class UserService {
    private UserRepository userRepository;
    private RegistrationTokenRepository registrationTokenRepository;
    private UserActivationService userActivationService;
    private PasswordEncoder encoder;

    @Autowired
    public UserService(
            UserRepository userRepository,
            RegistrationTokenRepository registrationTokenRepository,
            UserActivationService userActivationService, PasswordEncoder encoder
    ) {
        this.userRepository = userRepository;
        this.registrationTokenRepository = registrationTokenRepository;
        this.userActivationService = userActivationService;
        this.encoder = encoder;
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

    public User userForDetails(UserIdentifier userDetails) {
        return userRepository.findOneByEmail(userDetails.getEmail())
                .orElseThrow(EntityNotFoundException::new);
    }
}
