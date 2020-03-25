package com.progmasters.moovsmart.controller;

import com.progmasters.moovsmart.dto.messaging.TopicDto;
import com.progmasters.moovsmart.service.messaging.MessagingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/topics")
public class TopicController {
    private static final Logger logger = LoggerFactory.getLogger(TopicController.class);
    private MessagingService service;
    private UserDetailsFromSecurityContext userDetails;

    public TopicController(MessagingService service, UserDetailsFromSecurityContext userDetails) {
        this.service = service;
        this.userDetails = userDetails;
    }


    @PutMapping("/{topicId}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Void> message(@RequestBody String message, @PathVariable Long topicId) {
        service.saveMessage(
                topicId,
                userDetails.get().getUsername(),
                message);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{topicId}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<TopicDto> fetchTopic(@PathVariable Long topicId) {
        return ResponseEntity.ok(
                TopicDto.fromTopic(
                        service.getTopic(topicId)
                )
        );
    }

    @GetMapping("/my-topics")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Map<Long, TopicDto>>
    fetchTopicsByUser() {
        return ResponseEntity.ok(
                service.getTopicsByUser(
                        userDetails.get()
                )
        );
    }

    @PostMapping("/subscribe")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Long> subscribeToTopic(@RequestBody Long advertId) {

        final String userName = userDetails.get().getUsername();

        return ResponseEntity.ok(
                service.getTopic(advertId, userName)
                        .orElseGet(() -> service.openTopic(advertId, userName))
                        .getId()
        );
    }
}
