package com.progmasters.moovsmart.repository;

import com.progmasters.moovsmart.domain.user.User;
import com.progmasters.moovsmart.domain.user.UserIdentifier;
import com.progmasters.moovsmart.domain.user.UserRole;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void createUser() {
        User user = new User("akarmi@akarmi.com", "user", "1234", UserRole.ROLE_USER);
        User user2 = new User("akarmi2@akarmi.com", "user2", "1235", UserRole.ROLE_USER);
        userRepository.save(user);
        userRepository.save(user2);
    }

    @Test
    void testSaveUserWithTwoHits() {
        assertEquals(2, userRepository.findAll().size());
    }

    @Test
    void testSaveUserWithThreeHits() {
        User user1 = new User("akarmi1@akarmi.com", "user1", "2345", UserRole.ROLE_USER);
        userRepository.save(user1);
        assertEquals(3, userRepository.findAll().size());
    }

    @Test
    void testFindUserByUsernameForOneHit() {
        Optional<User> optUser = userRepository.findOneByUserName("user");
        List<User> userList = new ArrayList<>();
        if(optUser.isPresent()) {
            User user = optUser.get();
            userList.add(user);
        }
        assertEquals(1,userList.size());
    }

    @Test
    void testFindUserByUserNameWithZeroHit() {
        Optional<User> optUser = userRepository.findOneByUserName("testuser");
        List<User> userList = new ArrayList<>();
        if(optUser.isPresent()) {
            User user = optUser.get();
            userList.add(user);
        }
        assertEquals(0,userList.size());
    }

    @Test
    void testGet() {
        User user2 = new User("akarmi2@akarmi.com", "user2", "1235", UserRole.ROLE_USER);
        UserIdentifier userIdentifier = UserIdentifier.forUser(user2);
        userIdentifier.setId(1L);
        User resultUser = userRepository.get(userIdentifier);
        List<User> userList = new ArrayList<>();
        userList.add(resultUser);
        assertEquals(1, userList.size());
    }

}
