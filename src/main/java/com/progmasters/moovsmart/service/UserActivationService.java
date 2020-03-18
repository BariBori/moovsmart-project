package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.domain.RegistrationToken;
import com.progmasters.moovsmart.domain.User;
import com.progmasters.moovsmart.repository.RegistrationTokenRepository;
import com.progmasters.moovsmart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@Service
@Transactional
public class UserActivationService {
    private RegistrationTokenRepository registrationTokenRepository;
    private UserRepository userRepository;
    private JavaMailSender mailSender;

    @Autowired
    public UserActivationService(RegistrationTokenRepository registrationTokenRepository,
                                 UserRepository userRepository,
                                 JavaMailSender mailSender) {
        this.registrationTokenRepository = registrationTokenRepository;
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }

    public User activateUserByTokenId(UUID uuid) {
        User user = registrationTokenRepository.findByUuid(uuid)
                .map(RegistrationToken::getUser)
                .orElseThrow(() ->
                        new EntityNotFoundException("no User found for this Token Id"));
        user.confirmRegistration();
        return userRepository.save(user);
    }

    public void sendActivationEmail(RegistrationToken token) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom("moovsmartaltenter@gmail.com");
        mail.setTo(token.getUser().getEmail());
        mail.setSubject("Aktivalja emailcimet");
        mail.setText("http://localhost:8080/api/users/activate/" + token.getUuid().toString());
        mailSender.send(mail);
    }
}
