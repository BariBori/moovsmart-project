package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.User;

public class UserDto {
    private Long id;
    private String email;
    private PersonalDetailsDto personalDetails;

    public static UserDto fromUser(User user) {
        UserDto result = new UserDto();
        result.id = user.getId();
        result.email = user.getEmail();
        result.personalDetails = PersonalDetailsDto.fromPersonalDetails(user.getPersonalDetails());
        return result;
    }
}
