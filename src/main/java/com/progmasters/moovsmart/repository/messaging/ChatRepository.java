package com.progmasters.moovsmart.repository.messaging;

import com.progmasters.moovsmart.domain.messaging.Chat;
import com.progmasters.moovsmart.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    @Query("select dm " +
            "from Chat dm " +
            "where (dm.enquirer= :user " +
            "or dm.advert.user= :user) " +
            "and dm.advert.id = :advertId")
    Optional<Chat> getByUserAndAdvert(User user, Long advertId);
}
