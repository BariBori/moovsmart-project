package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //TODO make disabled unless user confirmed reg
    public UserDetails loadUserByUsername(String email) {
        return userRepository.findByEmail(email)
                .map(user ->
                        org.springframework.security.core.userdetails.User.builder()
                                .authorities(new SimpleGrantedAuthority("USER"))
                                .username(user.getEmail())
                                .password(user.getPasswordHash())
                                .disabled(false)
                                .build())
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "No user found with username: " + email)
                );
    }
}