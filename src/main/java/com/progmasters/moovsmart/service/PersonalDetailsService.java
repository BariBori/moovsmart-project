package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.domain.PersonalDetails;
import com.progmasters.moovsmart.dto.PersonalDetailsDto;
import com.progmasters.moovsmart.repository.PersonalDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class PersonalDetailsService {
    private PersonalDetailsRepository repository;

    @Autowired
    public PersonalDetailsService(PersonalDetailsRepository repository) {
        this.repository = repository;
    }

    public PersonalDetails save(PersonalDetailsDto personalDetailsDto) {
        return repository.save(
                new PersonalDetails(personalDetailsDto.getFullName())
        );
    }
}
