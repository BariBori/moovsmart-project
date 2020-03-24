package com.progmasters.moovsmart.repository.messaging;

import com.progmasters.moovsmart.domain.messaging.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepostitory extends JpaRepository<Topic, Long> {
    Optional<Topic> findOneById(Long id);
}
