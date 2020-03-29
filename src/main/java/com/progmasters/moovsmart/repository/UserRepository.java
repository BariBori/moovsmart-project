package com.progmasters.moovsmart.repository;

import com.progmasters.moovsmart.domain.user.User;
import com.progmasters.moovsmart.domain.user.UserIdentifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByUserName(String userName);

    default User get(UserIdentifier user) {
        return findByEmail(user.getEmail())
                .orElseThrow(() ->
                        new EntityNotFoundException("that user doesn't exist")
                );
    }
}
