package com.progmasters.moovsmart.repository.messaging;

import com.progmasters.moovsmart.domain.PropertyAdvert;
import com.progmasters.moovsmart.domain.messaging.Topic;
import com.progmasters.moovsmart.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.stream.Stream;

public interface TopicRepostitory extends JpaRepository<Topic, Long> {
    Optional<Topic> findOneById(Long id);

    Optional<Topic> findOneByAdvertAndEnquirer(PropertyAdvert advert, User enquirer);

    @Query("select t " +
            "from Topic t " +
            "where t.advertiser.id = :userId " +
            "or t.enquirer.id = :userId")
    Stream<Topic> streamByAdvertiserOrEnquirer(Long userId);
}
