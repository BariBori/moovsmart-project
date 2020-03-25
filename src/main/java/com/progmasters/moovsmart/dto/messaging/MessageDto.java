package com.progmasters.moovsmart.dto.messaging;

import com.progmasters.moovsmart.domain.messaging.Message;

import java.time.Instant;

public class MessageDto {
    private String sender;
    private String text;
    private Instant sentAt;

    public static MessageDto fromMessage(Message message) {
        MessageDto result = new MessageDto();
        result.sender = message.getSender().getUserName();
        result.text = message.getText();
        result.sentAt = message.getSentAt();
        return result;
    }

    public Instant getSentAt() {
        return sentAt;
    }

    public String getSender() {
        return sender;
    }

    public String getText() {
        return text;
    }
}
