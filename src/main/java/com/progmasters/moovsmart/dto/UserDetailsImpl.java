package com.progmasters.moovsmart.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.progmasters.moovsmart.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    @JsonIgnore
    private String password;
    private String userName;
    @JsonIgnore
    private Boolean enabled;
    private List<GrantedAuthority> authorities = new ArrayList<>();

    public static UserDetailsImpl forUser(User user) {
        UserDetailsImpl result = new UserDetailsImpl();
        result.userName = user.getEmail();
        result.password = user.getPasswordHash();
        result.enabled = user.isActivated();
        user.getUserRoles().forEach(role ->
                result.authorities.add(new SimpleGrantedAuthority(role.name())));
        return result;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
