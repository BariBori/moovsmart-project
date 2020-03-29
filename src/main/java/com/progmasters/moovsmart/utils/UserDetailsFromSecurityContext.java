package com.progmasters.moovsmart.utils;

import com.progmasters.moovsmart.domain.user.UserIdentifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class UserDetailsFromSecurityContext implements Supplier<UserIdentifier> {
    @Override
    public UserIdentifier get() {
        return (UserIdentifier)
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();
    }
}
