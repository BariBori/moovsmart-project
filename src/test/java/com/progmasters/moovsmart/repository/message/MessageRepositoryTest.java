package com.progmasters.moovsmart.repository.message;

import com.progmasters.moovsmart.domain.messaging.Conversation;
import com.progmasters.moovsmart.domain.messaging.Message;
import com.progmasters.moovsmart.domain.user.User;
import com.progmasters.moovsmart.domain.user.UserRole;
import com.progmasters.moovsmart.repository.UserRepository;
import com.progmasters.moovsmart.repository.messaging.MessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class MessageRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MessageRepository messageRepository;

    @BeforeEach
    void createMessage() {
        User user = new User("akarmi@akarmi.com", "user", "1234", UserRole.ROLE_USER);
        userRepository.save(user);

        Conversation conversation = new Conversation() {
            @Override
            public List<Message> getMessages() {
                return super.getMessages();
            }
        };

        String actualMessage = "Message";
        Message message = new Message(user, conversation, actualMessage);
        messageRepository.save(message);

    }

    @Test
    void testSaveMessageWithOneHit() {
        List<Message> messageList = messageRepository.findAll();
        assertEquals(1, messageList.size());
    }



}
