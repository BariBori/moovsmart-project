package com.progmasters.moovsmart.dto.messaging;

public class MessageFormData {
    private Long topicId;
    private String sender;
    private String text;

    public Long getTopicId() {
        return topicId;
    }

    public String getSender() {
        return sender;
    }

    public String getText() {
        return text;
    }
}
