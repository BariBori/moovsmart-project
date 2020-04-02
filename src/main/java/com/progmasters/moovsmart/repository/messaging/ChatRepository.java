package com.progmasters.moovsmart.repository.messaging;

import com.progmasters.moovsmart.domain.messaging.Chat;
import com.progmasters.moovsmart.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    @Query("select chat " +
            "from Chat chat " +
            "where (chat.enquirer= :user " +
            "or chat.advert.user= :user) " +
            "and chat.advert.id = :advertId")
    Optional<Chat> getByUserAndAdvert(User user, Long advertId);
}
