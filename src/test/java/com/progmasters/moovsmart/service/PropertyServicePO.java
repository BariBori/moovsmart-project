package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.domain.*;
import com.progmasters.moovsmart.domain.user.User;
import com.progmasters.moovsmart.domain.user.UserIdentifier;
import com.progmasters.moovsmart.domain.user.UserRole;
import com.progmasters.moovsmart.dto.form.PropertyAdvertFormData;
import com.progmasters.moovsmart.dto.list.PropertyAdvertListItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@SpringBootTest
@Transactional
@Rollback
@AutoConfigureTestDatabase
public class PropertyServicePO {

    @Autowired
    private PropertyAdvertService advertService;

    @Test
    public void testEmptyPropertyList() {
        List<PropertyAdvertListItem> propertyList = advertService.listPropertyAdverts();
        assertEquals(0, propertyList.size());
    }

    @Test
    public void testPropertyListWithOneProperty() {
        PropertyAdvertFormData propertyAdvertFormData = new PropertyAdvertFormData();
        propertyAdvertFormData.setPrice(20.0);
        propertyAdvertFormData.setId(300000);
        propertyAdvertFormData.setPropertyType(PropertyType.FLAT);
        propertyAdvertFormData.setPropertyConditionType(PropertyConditionType.NEW);
        propertyAdvertFormData.setParkingType(ParkingType.STREET);
        propertyAdvertFormData.setTitle("Nagyon szép eladó lakás a kerületben, sok ablakkal a parkra");
        propertyAdvertFormData.setAddress("Budapest, Pusztaszeri út, Magyarország");
        propertyAdvertFormData.setLongitude(19.0242842);
        propertyAdvertFormData.setLatitude(47.5254524);
        propertyAdvertFormData.setCity("Budapest");
        propertyAdvertFormData.setStreet("Pusztaszeri út");
        propertyAdvertFormData.setArea(45);
        propertyAdvertFormData.setNumberOfRooms(3);
        propertyAdvertFormData.setElevator(true);
        propertyAdvertFormData.setDescription("A lakás 50 m2-es két szobás és erkélyes. A szobák laminált padlósak, a többi helyiség járólapos, nyílászárók cseréltek A lakás közös zárt folyosóról közelíthető meg, egy emeleten 4lakás, egy folyoson 2 lakás található.");
        propertyAdvertFormData.setPlaceId("EipCdWRhcGVzdCwgS29zc3V0aCBMYWpvcyB0w6lyLCAxMDU1IEh1bmdhcnkiLiosChQKEgnn2j6uEdxBRxG35SIm7l77zxIUChIJuYIq9hPcQUcROei1dj8q7xo");

        User user = new User("akarmi@akarmi.com", "user", "1234", UserRole.ROLE_USER);
        UserIdentifier.forUser(user);
        advertService.saveAdvert(propertyAdvertFormData, UserIdentifier.forUser(user));

        List<PropertyAdvertListItem> propertyList = advertService.listPropertyAdverts();
        assertEquals(1, propertyList.size());
    }

}
