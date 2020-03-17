package com.progmasters.moovsmart.integration;

import com.progmasters.moovsmart.dto.PropertyAdvertListItem;
import com.progmasters.moovsmart.service.PropertyAdvertService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@Transactional
@Rollback
@AutoConfigureTestDatabase
public class PropertyServicePO {

    @Autowired
    private PropertyAdvertService advertService;

    @Test
    public void testEmptyPropertyList(){

        List<PropertyAdvertListItem> propertyList = advertService.listPropertyAdverts();

        assertEquals(0, propertyList.size());

    }

}
