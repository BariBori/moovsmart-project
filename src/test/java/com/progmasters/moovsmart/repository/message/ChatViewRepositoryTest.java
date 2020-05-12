package com.progmasters.moovsmart.repository.message;

import com.progmasters.moovsmart.repository.messaging.ChatViewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ChatViewRepositoryTest {

    @Autowired
    private ChatViewRepository chatViewRepository;

    @BeforeEach
    void createChatView() {

    }
}
