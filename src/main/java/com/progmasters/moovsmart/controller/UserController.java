package com.progmasters.moovsmart.controller;

import com.progmasters.moovsmart.dto.UserForm;
import com.progmasters.moovsmart.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        //TODO add validator
        //binder.addValidators(new OrcDetailsValidator());
    }

    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody UserForm userFormData) {
        service.registerUser(userFormData);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/authenticate")
    public ResponseEntity<Void> authenticateUser() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/me")
    @Secured("USER")
    public ResponseEntity<Void> foo() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
