package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.domain.User;
import com.progmasters.moovsmart.dto.UserForm;
import com.progmasters.moovsmart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserService(
            UserRepository repository,
            PersonalDetailsService service,
            PasswordEncoder encoder
    ) {
        this.repository = repository;
        this.service = service;
        this.encoder = encoder;
    }

    public User registerUser(UserForm userDto) {
        return repository.save(
                new User(
                        userDto.getEmail(),
                        encoder.encode(userDto.getPassword()),
                        service.save(userDto.getPersonalDetails())
                )
        );
    }
}
