package com.progmasters.moovsmart.dto.messaging;

public class StompMessage<T> {
    private String recipient;
    private T payload;

    public StompMessage(String recipient, T payload) {
        this.recipient = recipient;
        this.payload = payload;
    }

    public String getRecipient() {
        return recipient;
    }

    public T getPayload() {
        return payload;
    }
}
