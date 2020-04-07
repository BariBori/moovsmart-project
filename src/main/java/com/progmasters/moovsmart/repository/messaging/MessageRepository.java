package com.progmasters.moovsmart.repository.messaging;

import com.progmasters.moovsmart.domain.messaging.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    void deleteAllByConversation_Id(Long conversationId);
}
