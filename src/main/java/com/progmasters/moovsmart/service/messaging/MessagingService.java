package com.progmasters.moovsmart.service.messaging;

import com.progmasters.moovsmart.domain.messaging.Message;
import com.progmasters.moovsmart.domain.messaging.Topic;
import com.progmasters.moovsmart.domain.user.UserDetailsImpl;
import com.progmasters.moovsmart.dto.messaging.TopicDto;
import com.progmasters.moovsmart.repository.AdvertRepository;
import com.progmasters.moovsmart.repository.UserRepository;
import com.progmasters.moovsmart.repository.messaging.MessageRepository;
import com.progmasters.moovsmart.repository.messaging.TopicRepostitory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class MessagingService {
    private TopicRepostitory topicRepostitory;
    private MessageRepository messageRepository;
    private UserRepository userRepository;
    private AdvertRepository advertRepository;

    public MessagingService(TopicRepostitory topicRepostitory, MessageRepository messageRepository, UserRepository userRepository, AdvertRepository advertRepository) {
        this.topicRepostitory = topicRepostitory;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
        this.advertRepository = advertRepository;
    }

    public Message saveMessage(Long topicId, String sender, String message) {
        return topicRepostitory.findOneById(topicId)
                .flatMap(topic -> userRepository.findByUserName(sender)
                        .map(user -> messageRepository.save(
                                new Message(topic, user, message)
                                )
                        )
                )
                .orElseThrow(EntityNotFoundException::new);
    }

    public Topic openTopic(Long advertId, String userName) {
        return advertRepository.findOneById(advertId)
                .flatMap(advert -> userRepository.findByUserName(userName)
                        .map(enquirer ->
                                topicRepostitory.save(new Topic(advert, enquirer))
                        )
                )
                .orElseThrow(EntityExistsException::new);
    }

    public Topic getTopic(Long topicId) {
        return topicRepostitory.findOneById(topicId)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Optional<Topic> getTopic(Long advertId, String userName) {
        return advertRepository.findOneById(advertId)
                .flatMap(advert -> userRepository.findByUserName(userName)
                        .flatMap(enquirer -> topicRepostitory.findOneByAdvertAndEnquirer(
                                advert,
                                enquirer
                                )
                        )
                );
    }

    public Map<Long, TopicDto> getTopicsByUser(UserDetailsImpl user) {
        return topicRepostitory.streamByAdvertiserOrEnquirer(user.getId())
                .collect(Collectors.toMap(
                        Topic::getId,
                        TopicDto::fromTopic
                ));
    }
}
