package com.progmasters.moovsmart.dto.messaging;


import com.progmasters.moovsmart.domain.messaging.Chat;

import java.util.List;
import java.util.stream.Collectors;

public class ChatDto {
    private Long advertId;
    private String title;
    private List<MessageDto> messages;

    public static ChatDto fromTopicView(Chat.View view) {
        ChatDto result = new ChatDto();
        result.advertId = view.getConversation().getAdvert().getId();
        result.title = view.getTitle();
        result.messages = view.getConversation()
                .getMessages().stream()
                .map(MessageDto::fromMessage)
                .collect(Collectors.toList());
        return result;
    }

    public Long getAdvertId() {
        return advertId;
    }

    public String getTitle() {
        return title;
    }

    public List<MessageDto> getMessages() {
        return messages;
    }
}
