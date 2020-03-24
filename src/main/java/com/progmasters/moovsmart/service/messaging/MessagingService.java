package com.progmasters.moovsmart.service.messaging;

import com.progmasters.moovsmart.domain.messaging.Message;
import com.progmasters.moovsmart.domain.messaging.Topic;
import com.progmasters.moovsmart.dto.messaging.MessageFormData;
import com.progmasters.moovsmart.dto.messaging.TopicFormData;
import com.progmasters.moovsmart.repository.AdvertRepository;
import com.progmasters.moovsmart.repository.UserRepository;
import com.progmasters.moovsmart.repository.messaging.TopicRepostitory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service
public class MessagingService {
    private TopicRepostitory topicRepostitory;
    private UserRepository userRepository;
    private AdvertRepository advertRepository;

    public MessagingService(TopicRepostitory topicRepostitory, UserRepository userRepository, AdvertRepository advertRepository) {
        this.topicRepostitory = topicRepostitory;
        this.userRepository = userRepository;
        this.advertRepository = advertRepository;
    }

    public Topic saveMessage(MessageFormData message) {
        return topicRepostitory.findOneById(message.getTopicId())
                .flatMap(topic ->
                        userRepository.findByUserName(message.getSender())
                                .map(user ->
                                        topic.addMessage(
                                                new Message(
                                                        user, message.getText()
                                                )
                                        )
                                )
                )
                .map(topicRepostitory::save)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Topic saveTopic(TopicFormData topic) {
        return advertRepository.findOneById(topic.getAdvertId())
                .flatMap(advert ->
                        userRepository.findByUserName(topic.getEnquirer())
                                .map(user ->
                                        topicRepostitory.save(
                                                new Topic(
                                                        advert,
                                                        user
                                                )
                                        ))
                )
                .orElseThrow(EntityNotFoundException::new);
    }
}
