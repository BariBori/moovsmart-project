package com.progmasters.moovsmart.service.user;

import com.progmasters.moovsmart.domain.user.UserDetailsImpl;
import com.progmasters.moovsmart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetailsImpl loadUserByUsername(String emailOrUserName) {
        //TODO rewrite to JPA
        return userRepository.findByEmail(emailOrUserName)
                .or(() ->
                        userRepository.findByUserName(emailOrUserName)
                )
                .map(UserDetailsImpl::forUser)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "No user found with te given email or username: "
                                        + emailOrUserName));
    }
}