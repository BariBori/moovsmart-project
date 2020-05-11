package com.progmasters.moovsmart.repository.user;

import com.progmasters.moovsmart.domain.user.RegistrationToken;
import com.progmasters.moovsmart.domain.user.User;
import com.progmasters.moovsmart.domain.user.UserRole;
import com.progmasters.moovsmart.repository.RegistrationTokenRepository;
import com.progmasters.moovsmart.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class RegistrationTokenTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegistrationTokenRepository registrationTokenRepository;

    @BeforeEach
    void createUser() {
        User user = new User("akarmi@akarmi.com", "user", "1234", UserRole.ROLE_USER);
        userRepository.save(user);
        RegistrationToken registrationToken = RegistrationToken.forUser(user);
        registrationTokenRepository.save(registrationToken);
    }

    @Test
    void testSaveRegistrationTokenWithOneHit() {
        assertEquals(1, registrationTokenRepository.findAll().size());
    }

    @Test
    void testFindByUUID() {
        User user1 = new User("akarmi1@akarmi.com", "user1", "1235", UserRole.ROLE_USER);
        userRepository.save(user1);
        RegistrationToken registrationToken = RegistrationToken.forUser(user1);
        UUID uuid = UUID.randomUUID();
        registrationToken.setUuid(uuid);
        registrationTokenRepository.save(registrationToken);
        Optional<RegistrationToken> resultUser = registrationTokenRepository.findByUuid(uuid);
        List<RegistrationToken> resultList = new ArrayList<>();
        if(resultUser.isPresent()) {
            RegistrationToken registrationToken1 = resultUser.get();
            resultList.add(registrationToken1);
        }
        assertEquals(1, resultList.size());
    }

    @Test
    void testFindUUIDWrongId() {
        Optional<RegistrationToken> resultUser = registrationTokenRepository.findByUuid(UUID.randomUUID());
        List<RegistrationToken> resultList = new ArrayList<>();
        if(resultUser.isPresent()) {
            RegistrationToken registrationToken1 = resultUser.get();
            resultList.add(registrationToken1);
        }
        assertEquals(0, resultList.size());
    }

}
