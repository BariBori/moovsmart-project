package com.progmasters.moovsmart.dto;

import com.progmasters.moovsmart.domain.PersonalDetails;

public class PersonalDetailsDto {
    private String fullName;

    public static PersonalDetailsDto fromPersonalDetails(PersonalDetails personalDetails) {
        PersonalDetailsDto result = new PersonalDetailsDto();
        result.fullName = personalDetails.getFullName();
        return result;
    }

}
