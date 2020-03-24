package com.progmasters.moovsmart.service.messaging;

import com.progmasters.moovsmart.domain.messaging.Topic;
import com.progmasters.moovsmart.dto.messaging.TopicFormData;
import com.progmasters.moovsmart.repository.AdvertRepository;
import com.progmasters.moovsmart.repository.UserRepository;
import com.progmasters.moovsmart.repository.messaging.TopicRepostitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service
public class TopicService {
    private TopicRepostitory topicRepostitory;
    private AdvertRepository advertRepository;
    private UserRepository userRepository;

    @Autowired
    public TopicService(TopicRepostitory topicRepostitory, AdvertRepository advertRepository, UserRepository userRepository) {
        this.topicRepostitory = topicRepostitory;
        this.advertRepository = advertRepository;
        this.userRepository = userRepository;
    }

    public Topic save(TopicFormData topic) {
        return advertRepository.findOneById(topic.getAdvertId())
                .flatMap(advert -> userRepository.findByUserName(topic.getEnquirer())
                        .map(enquirer ->
                                topicRepostitory.save(new Topic(advert, enquirer))
                        )
                )
                .orElseThrow(EntityNotFoundException::new);
    }
}
