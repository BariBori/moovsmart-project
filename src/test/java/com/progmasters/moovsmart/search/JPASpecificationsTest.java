package com.progmasters.moovsmart.search;

import com.progmasters.moovsmart.domain.PropertyAdvert;
import com.progmasters.moovsmart.domain.search.PropertySpecification;
import com.progmasters.moovsmart.domain.search.SearchCriteria;
import com.progmasters.moovsmart.dto.PropertyAdvertListItem;
import com.progmasters.moovsmart.repository.AdvertRepository;
import com.progmasters.moovsmart.service.PropertyAdvertService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/*import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
@Rollback
@AutoConfigureTestDatabase
public class JPASpecificationsTest {

    @Autowired
    private AdvertRepository advertRepository;
    private PropertyAdvertService advertService;

    @Test
    public void searchGivenCity(){
        PropertySpecification spec =
                new PropertySpecification(new SearchCriteria("city", ":","Budapest"));

       // List<PropertyAdvert> results = advertRepository.findAll(spec);
      //  assertEquals(4, results.size());

    }
}*/
