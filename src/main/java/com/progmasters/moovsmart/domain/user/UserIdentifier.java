package com.progmasters.moovsmart.domain.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserIdentifier implements UserDetails {
    private Long id;

    private String email;

    private String password;

    private String userName;

    private Boolean enabled;

    private List<GrantedAuthority> authorities = new ArrayList<>();

    public static UserIdentifier forUser(User user) {
        UserIdentifier result = new UserIdentifier();
        result.id = user.getId();
        result.userName = user.getUserName();
        result.email = user.getEmail();
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

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
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

    public void setId(Long id) {
        this.id = id;
    }
}
