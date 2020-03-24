package com.progmasters.moovsmart.controller;

import com.progmasters.moovsmart.dto.messaging.MessageFormData;
import com.progmasters.moovsmart.dto.messaging.TopicDto;
import com.progmasters.moovsmart.dto.messaging.TopicFormData;
import com.progmasters.moovsmart.service.messaging.MessagingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/messages")
public class MessagingController {
    private static final Logger logger = LoggerFactory.getLogger(MessagingController.class);
    private MessagingService service;

    public MessagingController(MessagingService service) {
        this.service = service;
    }


    @PostMapping
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Void> message(@RequestBody MessageFormData message) {
        service.saveMessage(message);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/topics/{id}")
    public ResponseEntity<TopicDto> fetchTopicById(@PathVariable Long id) {
        return ResponseEntity.ok(
                TopicDto.fromTopic(
                        service.getTopicById(id)
                )
        );
    }

    @PostMapping("/topics")
    public ResponseEntity<Void> openTopic(@RequestBody TopicFormData topic) {
        service.saveTopic(topic);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
