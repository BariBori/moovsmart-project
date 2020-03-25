package com.progmasters.moovsmart.dto.messaging;

import com.progmasters.moovsmart.domain.messaging.Message;

import java.time.Instant;
import java.time.LocalDateTime;

public class MessageDto {
    private String sender;
    private String text;
    private LocalDateTime sentAt;

    public static MessageDto fromMessage(Message message) {
        MessageDto result = new MessageDto();
        result.sender = message.getSender().getUserName();
        result.text = message.getText();
        result.sentAt = message.getSentAt();
        return result;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public String getSender() {
        return sender;
    }

    public String getText() {
        return text;
    }
}
