package com.progmasters.moovsmart.dto.user;

import com.progmasters.moovsmart.domain.user.UserIdentifier;

public class UserDto {
    private Long id;
    private String email;
    private String userName;

    public static UserDto fromUserDetails(UserIdentifier userDetails) {
        UserDto result = new UserDto();
        result.id = userDetails.getId();
        result.email = userDetails.getEmail();
        result.userName = userDetails.getUsername();
        return result;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }
}
