package com.progmasters.moovsmart.controller;

import com.progmasters.moovsmart.domain.User;
import com.progmasters.moovsmart.dto.UserForm;
import com.progmasters.moovsmart.service.UserActivationService;
import com.progmasters.moovsmart.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("api/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;
    private UserActivationService userActivationService;

    public UserController(UserService userService, UserActivationService userActivationService) {
        this.userService = userService;
        this.userActivationService = userActivationService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        //TODO add validator
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody UserForm userFormData) {
        userService.registerUser(userFormData);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/authenticate")
    public ResponseEntity<Void> authenticateUser() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/activate/{tokenId}")
    public ResponseEntity<String> activateUser(@PathVariable UUID tokenId) {
        User user = userActivationService.activateUserByTokenId(tokenId);
        return ResponseEntity.ok("User " + user.getEmail() + " activated");
    }

    @GetMapping("/me")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<Principal> userDetails(Principal principal) {
        return ResponseEntity.ok(principal);
    }

}
