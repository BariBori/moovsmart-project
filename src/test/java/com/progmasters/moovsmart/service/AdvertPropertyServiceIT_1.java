package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.dto.PropertyAdvertListItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailSender;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@Transactional
@Rollback
@AutoConfigureTestDatabase
public class AdvertPropertyServiceIT_1 {

    @Autowired
    private PropertyAdvertService propertyAdvertService;

    @Test
    public void testEmptyPropertyAdvertList() {
        List<PropertyAdvertListItem> advertList = propertyAdvertService.listPropertyAdverts();
        assertEquals(0, advertList.size());
    }


}
