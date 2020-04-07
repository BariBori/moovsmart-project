package com.progmasters.moovsmart.dto.messaging;


import com.progmasters.moovsmart.domain.messaging.Chat;

import java.util.List;
import java.util.stream.Collectors;

public class ChatDto {
    private Long id;
    private List<MessageDto> messages;

    public static ChatDto fromTopicView(Chat.View view) {
        ChatDto result = new ChatDto();
        result.id = view.getConversation().getId();
        result.messages = view.getConversation()
                .getMessages().stream()
                .map(MessageDto::fromMessage)
                .collect(Collectors.toList());
        return result;
    }

    public Long getId() {
        return id;
    }

    public List<MessageDto> getMessages() {
        return messages;
    }
}
