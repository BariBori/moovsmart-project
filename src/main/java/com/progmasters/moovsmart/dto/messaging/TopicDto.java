package com.progmasters.moovsmart.dto.messaging;

import com.progmasters.moovsmart.domain.messaging.Chat;

public class TopicDto {
    private Long advertId;
    private String title;
    private String partner;
    private Boolean unread;

    public static TopicDto fromView(Chat.View view) {
        TopicDto result = new TopicDto();
        result.advertId = view.getConversation().getAdvert().getId();
        result.title = view.getTitle();
        result.partner = view.getPartner().getUserName();
        return result;
    }

    public Long getAdvertId() {
        return advertId;
    }

    public String getTitle() {
        return title;
    }

    public String getPartner() {
        return partner;
    }

    public Boolean getUnread() {
        return unread;
    }
}
