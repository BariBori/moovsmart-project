package com.progmasters.moovsmart.repository.messaging;

import com.progmasters.moovsmart.domain.PropertyAdvert;
import com.progmasters.moovsmart.domain.messaging.Topic;
import com.progmasters.moovsmart.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepostitory extends JpaRepository<Topic, Long> {
    Optional<Topic> findOneById(Long id);

    Optional<Topic> findOneByAdvertAndEnquirer(PropertyAdvert advert, User enquirer);
}
