package com.progmasters.moovsmart.dto.messaging;


import com.progmasters.moovsmart.domain.messaging.Topic;

import java.util.List;
import java.util.stream.Collectors;

public class TopicDto {
    private Long advertId;
    private List<MessageDto> messages;

    public static TopicDto fromTopic(Topic topic) {
        TopicDto result = new TopicDto();
        result.advertId = topic.getAdvert().getId();
        result.messages = topic.getMessages().stream()
                .map(MessageDto::fromMessage)
                .collect(Collectors.toList());
        return result;
    }

    public Long getAdvertId() {
        return advertId;
    }

    public List<MessageDto> getMessages() {
        return messages;
    }
}
