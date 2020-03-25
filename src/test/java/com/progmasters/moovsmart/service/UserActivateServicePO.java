package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.repository.RegistrationTokenRepository;
import com.progmasters.moovsmart.repository.UserRepository;
import com.progmasters.moovsmart.service.user.UserActivationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@SpringBootTest
@Transactional
@Rollback
public class UserActivateServicePO {

    private UserActivationService userActivatingService;

    @Autowired
    private RegistrationTokenRepository registrationTokenRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender mailSender;

    @BeforeEach
    public void initTest(){
        this.userActivatingService =
                new UserActivationService(registrationTokenRepository,
                userRepository, mailSender);
    }

    @Test
    public void testEmptyPropertyList(){
        this.userActivatingService.activateUserByTokenId(new UUID(1L, 1L));
    }
}
