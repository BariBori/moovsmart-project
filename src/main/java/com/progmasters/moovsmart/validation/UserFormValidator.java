package com.progmasters.moovsmart.validation;

import com.progmasters.moovsmart.dto.UserForm;
import com.progmasters.moovsmart.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.function.Predicate;

@Component
public class UserFormValidator implements Validator {
    private UserService service;

    public UserFormValidator(UserService service) {
        this.service = service;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Predicate<String> validEmail = email -> {
            boolean valid;
            try {
                new InternetAddress(email).validate();
                valid = true;
            } catch (AddressException ex) {
                valid = false;
            }
            return valid;
        };
        var userForm = (UserForm) target;
        String email = userForm.getEmail();
        if (Boolean.TRUE.equals(service.isEmailTaken(email))) {
            errors.rejectValue("email", "moovsmart.user.email.alreadyTaken");
        } else if (!validEmail.test(email)) {
            errors.rejectValue("email", "moovsmart.user.email.invalid");
        }
        if (userForm.getPassword().length() < 4) {
            errors.rejectValue("password", "moovsmart.user.password.invalid");
        }

    }
}
