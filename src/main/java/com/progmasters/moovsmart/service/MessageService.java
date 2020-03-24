package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.domain.messaging.Message;
import com.progmasters.moovsmart.dto.messaging.MessageFormData;
import com.progmasters.moovsmart.repository.messaging.MessageRepository;
import com.progmasters.moovsmart.repository.messaging.TopicRepostitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MessageService {
    private MessageRepository msgRepository;
    private TopicRepostitory topicRepostitory;

    @Autowired
    public MessageService(MessageRepository msgRepository, TopicRepostitory topicRepostitory) {
        this.msgRepository = msgRepository;
        this.topicRepostitory = topicRepostitory;
    }

    public Message saveMessage(MessageFormData message) {
        return repository.save()
    }
}
