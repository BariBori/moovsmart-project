package com.progmasters.moovsmart.repository;
import com.progmasters.moovsmart.dto.form.BidFormData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;


@ExtendWith(SpringExtension.class)
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class BidRepositoryTest {

    @Autowired
    private BidRepository bidRepository;

//    @Test
//
//    public void testSaveBid() {
//
//    }
}
