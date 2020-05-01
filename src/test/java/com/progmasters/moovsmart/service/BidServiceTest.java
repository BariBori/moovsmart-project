package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.domain.Bid;
import com.progmasters.moovsmart.domain.PropertyAdvert;
import com.progmasters.moovsmart.repository.BidRepository;
import com.progmasters.moovsmart.service.PropertyAdvertService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback
@AutoConfigureTestDatabase
public class BidServiceTest {


    private BidRepository bidRepository;

    @Autowired
    private PropertyAdvertService propertyAdvertService;

    @Test
    public void testSaveBid() {




    }



}
