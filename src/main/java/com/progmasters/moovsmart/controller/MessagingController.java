package com.progmasters.moovsmart.controller;

import com.progmasters.moovsmart.dto.messaging.ChatDto;
import com.progmasters.moovsmart.dto.messaging.TopicDto;
import com.progmasters.moovsmart.service.messaging.MessagingService;
import com.progmasters.moovsmart.utils.UserDetailsFromSecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/messages")
public class MessagingController {
    private static final Logger logger = LoggerFactory.getLogger(MessagingController.class);
    private MessagingService service;
    private UserDetailsFromSecurityContext userDetails;

    @Autowired
    public MessagingController(MessagingService service, UserDetailsFromSecurityContext userDetails) {
        this.service = service;
        this.userDetails = userDetails;
    }

    @PutMapping("/topic/{chatId}")
    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    public ResponseEntity<ChatDto> directMessage(@RequestBody String message, @PathVariable Long chatId) {
        logger.info("Message saved");
        return service.saveDirectMessage(userDetails.get(), message, chatId).map(ChatDto::fromTopicView)
                .map(ResponseEntity::ok).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/my-topics")
    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    public ResponseEntity<Map<Long, TopicDto>> fetchTopicsByUser() {
        return ResponseEntity.ok(service.getTopicsByUserIdentifier(userDetails.get()));
    }

    @PostMapping("/unsubscribe/{chatId}")
    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    public ResponseEntity<Map<Long, TopicDto>> unsubscribeFromDirectMessaging(@PathVariable Long chatId) {
        return ResponseEntity.ok(service.deleteChatView(userDetails.get(), chatId));
    }

    @PostMapping("/direct/{advertId}")
    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    public ResponseEntity<Map<Long, TopicDto>> directMessage(@PathVariable Long advertId) {
        return ResponseEntity.ok(service.enquire(userDetails.get(), advertId));
    }

    @GetMapping("/topic/{chatId}")
    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    public ResponseEntity<ChatDto> chat(@PathVariable Long chatId) {
        return service.renderUserViewForChat(userDetails.get(), chatId)
                .map(view -> ResponseEntity.ok(ChatDto.fromTopicView(view)))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }
}
