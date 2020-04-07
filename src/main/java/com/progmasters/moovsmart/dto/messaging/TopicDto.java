package com.progmasters.moovsmart.dto.messaging;

import com.progmasters.moovsmart.domain.messaging.Chat;

public class TopicDto {
    private Long chatId;
    private String title;
    private String partner;
    private Integer unread;

    public static TopicDto fromView(Chat.View view) {
        TopicDto result = new TopicDto();
        result.chatId = view.getConversation().getId();
        result.title = view.getTitle();
        result.partner = view.getPartner().getUserName();
        result.unread=view.getUnread();
        return result;
    }

    public Long getChatId() {
        return chatId;
    }

    public String getTitle() {
        return title;
    }

    public String getPartner() {
        return partner;
    }

    public Integer getUnread() {
        return unread;
    }
}
