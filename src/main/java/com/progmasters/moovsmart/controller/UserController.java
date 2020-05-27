package com.progmasters.moovsmart.controller;

import com.progmasters.moovsmart.domain.user.User;
import com.progmasters.moovsmart.dto.user.UserDto;
import com.progmasters.moovsmart.dto.user.UserForm;
import com.progmasters.moovsmart.service.user.UserActivationService;
import com.progmasters.moovsmart.service.user.UserService;
import com.progmasters.moovsmart.utils.UserDetailsFromSecurityContext;
import com.progmasters.moovsmart.validation.UserFormValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("api/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;
    private UserActivationService userActivationService;
    private UserFormValidator formValidator;
    private UserDetailsFromSecurityContext userDetails;

    @Autowired
    public UserController(UserService userService, UserActivationService userActivationService,
            UserFormValidator formValidator, UserDetailsFromSecurityContext userDetails) {
        this.userService = userService;
        this.userActivationService = userActivationService;
        this.formValidator = formValidator;
        this.userDetails = userDetails;
    }

    @InitBinder("userForm")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(formValidator);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody UserForm userForm) {
        userService.registerUser(userForm);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/activate/{tokenId}")
    public ResponseEntity<String> activateUser(@PathVariable UUID tokenId) {
        User user = userActivationService.activateUserByTokenId(tokenId);
        return ResponseEntity.ok("User " + user.getEmail() + " activated");
    }

    @PostMapping("/authenticate")
    public ResponseEntity<UserDto> authenticateUser() {
        logger.info(String.format("User %s was logged in", userDetails.get().getUsername()));
        return ResponseEntity.ok(UserDto.fromUserDetails(userDetails.get()));
    }

    @GetMapping("/me")
    @Secured({ "ROLE_USER", "ROLE_ADMIN" })
    public ResponseEntity<UserDto> currentUserId() {
        return ResponseEntity.ok(UserDto.fromUserDetails(userDetails.get()));
    }

}
