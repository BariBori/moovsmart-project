package com.progmasters.moovsmart.controller;

import com.progmasters.moovsmart.dto.messaging.ChatDto;
import com.progmasters.moovsmart.dto.messaging.MessageDto;
import com.progmasters.moovsmart.dto.messaging.TopicDto;
import com.progmasters.moovsmart.service.messaging.MessagingService;
import com.progmasters.moovsmart.utils.UserDetailsFromSecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/messages")
public class MessagingController {
    private static final Logger logger = LoggerFactory.getLogger(MessagingController.class);
    private MessagingService service;
    private UserDetailsFromSecurityContext userDetails;

    public MessagingController(
            MessagingService service,
            UserDetailsFromSecurityContext userDetails) {
        this.service = service;
        this.userDetails = userDetails;
    }


    @PutMapping("/topic/{advertId}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<MessageDto> directMessage(
            @RequestBody String message,
            @PathVariable Long advertId
    ) {
        return service.isSubscribed(userDetails.get(), advertId)
                ? ResponseEntity.ok(MessageDto.fromMessage(
                service.saveDirectMessage(
                        userDetails.get(),
                        message,
                        advertId)))
                : new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/my-topics")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<TopicDto>>
    fetchTopicsByUser() {
        return ResponseEntity.ok(
                service.getTopicsByUser(
                        userDetails.get()
                ));
    }

    @PostMapping("/unsubscribe/{advertId}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Void> unsubscribeFromDirectMessaging(@PathVariable Long advertId) {
        service.deleteChatView(userDetails.get(), advertId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/direct/{advertId}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Void> directMessage(@PathVariable Long advertId) {
        service.enquire(userDetails.get(), advertId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/topic/{advertId}")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<ChatDto> chat(@PathVariable Long advertId) {
        return service.renderUserViewForChat(userDetails.get(), advertId)
                .map(view ->
                        ResponseEntity.ok(ChatDto.fromTopicView(view)))
                .orElse(new ResponseEntity<>(HttpStatus.UNAUTHORIZED));

    }
}
