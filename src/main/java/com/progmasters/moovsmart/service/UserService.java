package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.domain.User;
import com.progmasters.moovsmart.dto.UserDto;
import com.progmasters.moovsmart.repository.PersonalDetailsRepository;
import com.progmasters.moovsmart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserService {
    private UserRepository repository;
    private PersonalDetailsService service;
    private PasswordEncoder encoder;

    @Autowired
    public UserService(UserRepository repository,
                       PersonalDetailsRepository detailsRepository,
                       PersonalDetailsService service,
                       PasswordEncoder encoder) {
        this.repository = repository;
        this.service = service;
        this.encoder = encoder;
    }

    private String encryptPassword(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    public User saveUser(UserDto userDto) {
        return repository.save(
                new User(
                        userDto.getEmail(),
                        encryptPassword(userDto.getPasswordEncoded()),
                        service.save(userDto.getPersonalDetails())
                )
        );
    }
}
