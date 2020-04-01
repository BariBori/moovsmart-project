package com.progmasters.moovsmart.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class SocketController {
    private final SimpMessagingTemplate simpMessagingTemplate;

    public SocketController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/secured/room")
    public <T> void sendTo(String recipient,
                           @Payload Map.Entry<String, T> msg) {
        simpMessagingTemplate.convertAndSendToUser(
                recipient,
                "/secured/user/queue/user-name",
                msg
        );
    }
}
