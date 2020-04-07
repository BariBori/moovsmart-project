package com.progmasters.moovsmart.service.messaging;

import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NotificationService {

    private final SimpMessageSendingOperations msgOps;

    public NotificationService(SimpMessageSendingOperations msgOps) {
        this.msgOps = msgOps;
    }

    public <T> Map.Entry<String, T> notifyUser(String userName, Map.Entry<String, T> notification) {
        msgOps.convertAndSendToUser(
                userName,
                "/queue/notify",
                Map.entry(notification.getKey(), notification.getValue())
        );
        return notification;
    }
}
