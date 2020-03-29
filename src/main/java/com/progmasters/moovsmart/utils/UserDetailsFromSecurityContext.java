package com.progmasters.moovsmart.utils;

import com.progmasters.moovsmart.domain.user.UserDetailsImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.function.Supplier;

@Component
public class UserDetailsFromSecurityContext implements Supplier<UserDetailsImpl> {
    @Override
    public UserDetailsImpl get() {
        return (UserDetailsImpl)
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal();
    }
}
