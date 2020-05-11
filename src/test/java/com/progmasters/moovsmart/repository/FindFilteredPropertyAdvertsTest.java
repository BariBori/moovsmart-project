package com.progmasters.moovsmart.repository;

import com.progmasters.moovsmart.domain.*;
import com.progmasters.moovsmart.domain.user.User;
import com.progmasters.moovsmart.domain.user.UserRole;
import com.progmasters.moovsmart.dto.form.PropertyAdvertFormData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertEquals;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class FindFilteredPropertyAdvertsTest {

    @Autowired
    private AdvertRepository advertRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void createUserAndAdverts() {

        User user = new User("akarmi@akarmi.com", "user", "1234", UserRole.ROLE_USER);
        userRepository.save(user);
        User user1 = new User("akarmi1@akarmi1.com", "user1", "2345", UserRole.ROLE_USER);
        userRepository.save(user1);

        PropertyAdvertFormData propertyAdvertFormData = new PropertyAdvertFormData();
        propertyAdvertFormData.setPrice(40.0);
        propertyAdvertFormData.setId(300000);
        propertyAdvertFormData.setPropertyType(PropertyType.FLAT);
        propertyAdvertFormData.setPropertyConditionType(PropertyConditionType.NEW);
        propertyAdvertFormData.setParkingType(ParkingType.STREET);
        propertyAdvertFormData.setTitle("Nagyon szép eladó lakás a kerületben");
        propertyAdvertFormData.setPlaceId("EipCdWRhcGVzdCwgS29zc3V0aCBMYWpvcyB0w6lyLCAxMDU1IEh1bmdhcnkiLiosChQKEgnn2j6uEdxBRxG35SIm7l77zxIUChIJuYIq9hPcQUcROei1dj8q7xo");
        propertyAdvertFormData.setLongitude(19.0242842);
        propertyAdvertFormData.setLatitude(47.5254524);
        propertyAdvertFormData.setAddress("Szeged, Pusztaszeri út, Magyarország");
        propertyAdvertFormData.setCity("Szeged");
        propertyAdvertFormData.setDistrict("XI. kerület");
        propertyAdvertFormData.setStreet("Pusztaszeri út");
        propertyAdvertFormData.setArea(85);
        propertyAdvertFormData.setNumberOfRooms(6);
        propertyAdvertFormData.setElevator(true);
        propertyAdvertFormData.setBalcony(false);
        propertyAdvertFormData.setDescription("A lakás 50 m2-es két szobás és erkélyes. A szobák laminált padlósak, a többi helyiség járólapos, nyílászárók cseréltek A lakás közös zárt folyosóról közelíthető meg, egy emeleten 4lakás, egy folyoson 2 lakás található.");

        PropertyAdvertFormData propertyAdvertFormData1 = new PropertyAdvertFormData();
        propertyAdvertFormData1.setPrice(20.0);
        propertyAdvertFormData1.setId(300000);
        propertyAdvertFormData1.setPropertyType(PropertyType.HOUSE);
        propertyAdvertFormData1.setPropertyConditionType(PropertyConditionType.RENEWED);
        propertyAdvertFormData1.setParkingType(ParkingType.STREET);
        propertyAdvertFormData1.setTitle("Nagyon szép eladó lakás a kerületben");
        propertyAdvertFormData1.setPlaceId("EipCdWRhcGVzdCwgS29zc3V0aCBMYWpvcyB0w6lyLCAxMDU1IEh1bmdhcnkiLiosChQKEgnn2j6uEdxBRxG35SIm7l77zxIUChIJuYIq9hPcQUcROei1dj8q7xo");
        propertyAdvertFormData1.setLongitude(19.0242842);
        propertyAdvertFormData1.setLatitude(47.5254524);
        propertyAdvertFormData1.setAddress("Budapest, Pusztaszeri út, Magyarország");
        propertyAdvertFormData1.setCity("Budapest");
        propertyAdvertFormData1.setDistrict("XI. kerület");
        propertyAdvertFormData1.setStreet("Pusztaszeri út");
        propertyAdvertFormData1.setArea(45);
        propertyAdvertFormData1.setNumberOfRooms(3);
        propertyAdvertFormData1.setElevator(true);
        propertyAdvertFormData1.setBalcony(false);
        propertyAdvertFormData1.setDescription("A lakás 50 m2-es két szobás és erkélyes. A szobák laminált padlósak, a többi helyiség járólapos, nyílászárók cseréltek.");

        PropertyAdvertFormData propertyAdvertFormData2 = new PropertyAdvertFormData();
        propertyAdvertFormData2.setPrice(40.0);
        propertyAdvertFormData2.setId(300000);
        propertyAdvertFormData2.setPropertyType(PropertyType.FLAT);
        propertyAdvertFormData2.setPropertyConditionType(PropertyConditionType.NEW);
        propertyAdvertFormData2.setParkingType(ParkingType.STREET);
        propertyAdvertFormData2.setTitle("Nagyon szép eladó lakás a kerületben");
        propertyAdvertFormData2.setPlaceId("EipCdWRhcGVzdCwgS29zc3V0aCBMYWpvcyB0w6lyLCAxMDU1IEh1bmdhcnkiLiosChQKEgnn2j6uEdxBRxG35SIm7l77zxIUChIJuYIq9hPcQUcROei1dj8q7xo");
        propertyAdvertFormData2.setLongitude(19.0242842);
        propertyAdvertFormData2.setLatitude(47.5254524);
        propertyAdvertFormData2.setAddress("Budapest, Pusztaszeri út, Magyarország");
        propertyAdvertFormData2.setCity("Budapest");
        propertyAdvertFormData2.setDistrict("XI. kerület");
        propertyAdvertFormData2.setStreet("Pusztaszeri út");
        propertyAdvertFormData2.setArea(85);
        propertyAdvertFormData2.setNumberOfRooms(6);
        propertyAdvertFormData2.setElevator(true);
        propertyAdvertFormData2.setBalcony(false);
        propertyAdvertFormData2.setDescription("A lakás 50 m2-es két szobás és erkélyes. A szobák laminált padlósak, a többi helyiség járólapos, nyílászárók cseréltek A lakás közös zárt folyosóról közelíthető meg, egy emeleten 4lakás, egy folyoson 2 lakás található.");

        PropertyAdvert propertyAdvert = new PropertyAdvert(propertyAdvertFormData, user);
        advertRepository.save(propertyAdvert);

        PropertyAdvert propertyAdvert1 = new PropertyAdvert(propertyAdvertFormData1, user1);
        advertRepository.save(propertyAdvert1);

        PropertyAdvert propertyAdvert2 = new PropertyAdvert(propertyAdvertFormData2, user1);
        advertRepository.save(propertyAdvert2);

    }

    @Test
    public void testFullSearchForOneFound() {
        List<PropertyAdvert> filteredList = advertRepository.findFilteredPropertyAdverts("Budapest", 10.0, 30.0,
                30, 60, 2, 4, PropertyType.HOUSE, PropertyConditionType.RENEWED,
                AdvertStatusType.FORAPPROVAL);
        assertEquals(1, filteredList.size());
    }

    @Test
    public void testEmptySearch() {
        List<PropertyAdvert> filteredList = advertRepository.findFilteredPropertyAdverts("", 0.0,
                Double.MAX_VALUE, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE,
                null, null, AdvertStatusType.FORAPPROVAL);
        assertEquals(3, filteredList.size());
    }

    @Test
    public void testSearchWithCity() {
        List<PropertyAdvert> filteredList = advertRepository.findFilteredPropertyAdverts("Budapest", 0.0,
                Double.MAX_VALUE, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE,
                null, null, AdvertStatusType.FORAPPROVAL);
        assertEquals(2, filteredList.size());
    }

    @Test
    public void testSearchWithCityAndConditionType() {
        List<PropertyAdvert> filteredList = advertRepository.findFilteredPropertyAdverts("Budapest", 0.0,
                Double.MAX_VALUE, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE,
                null, PropertyConditionType.RENEWED, AdvertStatusType.FORAPPROVAL);
        assertEquals(1, filteredList.size());
    }

    @Test
    public void testSearchWithCityAndPropertyType() {
        List<PropertyAdvert> filteredList = advertRepository.findFilteredPropertyAdverts("Budapest", 0.0,
                Double.MAX_VALUE, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE,
                PropertyType.FLAT, null, AdvertStatusType.FORAPPROVAL);
        assertEquals(1, filteredList.size());
    }

    @Test
    public void testSearchWithCityAndPriceForTwoHits() {
        List<PropertyAdvert> filteredList = advertRepository.findFilteredPropertyAdverts("Budapest", 18.0,
                42.0, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE,
                null, null, AdvertStatusType.FORAPPROVAL);
        assertEquals(2, filteredList.size());
    }

    @Test
    public void testSearchWithCityAndPriceForOneHit() {
        List<PropertyAdvert> filteredList = advertRepository.findFilteredPropertyAdverts("Budapest", 28.0,
                42.0, 0, Integer.MAX_VALUE, 0, Integer.MAX_VALUE,
                null, null, AdvertStatusType.FORAPPROVAL);
        assertEquals(1, filteredList.size());
    }

    @Test
    public void testSearchWithCityAndNumberOfRoomsForOneHit() {
        List<PropertyAdvert> filteredList = advertRepository.findFilteredPropertyAdverts("Budapest", 0.0,
                Double.MAX_VALUE, 0, Integer.MAX_VALUE, 2, 3,
                null, null, AdvertStatusType.FORAPPROVAL);
        assertEquals(1, filteredList.size());
    }

    @Test
    public void testSearchWithCityAndNumberOfRoomsForTwoHits() {
        List<PropertyAdvert> filteredList = advertRepository.findFilteredPropertyAdverts("Budapest", 0.0,
                Double.MAX_VALUE, 0, Integer.MAX_VALUE, 2, 7,
                null, null, AdvertStatusType.FORAPPROVAL);
        assertEquals(2, filteredList.size());
    }

    @Test
    public void testSearchWithNumberOfRoomsForThreeHits() {
        List<PropertyAdvert> filteredList = advertRepository.findFilteredPropertyAdverts("", 0.0,
                Double.MAX_VALUE, 0, Integer.MAX_VALUE, 2, 7,
                null, null, AdvertStatusType.FORAPPROVAL);
        assertEquals(3, filteredList.size());
    }

    @Test
    public void testSearchWithAreaForTwoHits() {
        List<PropertyAdvert> filteredList = advertRepository.findFilteredPropertyAdverts("", 0.0,
                Double.MAX_VALUE, 50, 90, 0, Integer.MAX_VALUE,
                null, null, AdvertStatusType.FORAPPROVAL);
        assertEquals(2, filteredList.size());
    }

}
