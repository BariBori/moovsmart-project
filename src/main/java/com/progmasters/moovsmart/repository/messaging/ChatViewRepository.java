package com.progmasters.moovsmart.repository.messaging;

import com.progmasters.moovsmart.domain.messaging.Chat;
import com.progmasters.moovsmart.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface ChatViewRepository extends JpaRepository<Chat.View, Long> {
    Stream<Chat.View> streamAllByUser(User user);

    List<Chat.View> findAllByConversation_Id(Long chatId);

    Optional<Chat.View> findOneByUser_IdAndConversation_Id(Long userId, Long chatId);
    Optional<Chat.View> findOneByPartner_IdAndConversation_Id(Long userId, Long chatId);



}
