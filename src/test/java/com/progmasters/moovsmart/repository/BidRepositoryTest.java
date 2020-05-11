package com.progmasters.moovsmart.repository;

import com.progmasters.moovsmart.domain.*;
import com.progmasters.moovsmart.domain.user.User;
import com.progmasters.moovsmart.domain.user.UserRole;
import com.progmasters.moovsmart.dto.form.BidFormData;
import com.progmasters.moovsmart.dto.form.PropertyAdvertFormData;
import com.progmasters.moovsmart.dto.list.BidListItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class bidRepositoryTest {

    @Autowired
    private AdvertRepository advertRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BidRepository bidRepository;

    @BeforeEach
    public void createUserAndAdverts() {

        User user = new User("akarmi@akarmi.com", "user", "1234", UserRole.ROLE_USER);
        userRepository.save(user);

        User user1 = new User("akarmi1@akarmi.com", "user1", "1234", UserRole.ROLE_USER);
        userRepository.save(user1);

        PropertyAdvertFormData propertyAdvertFormData = new PropertyAdvertFormData();
        propertyAdvertFormData.setPrice(40.0);
        propertyAdvertFormData.setId(1l);
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
        propertyAdvertFormData1.setId(1l);
        propertyAdvertFormData1.setPrice(40.0);
        propertyAdvertFormData1.setPropertyType(PropertyType.FLAT);
        propertyAdvertFormData1.setPropertyConditionType(PropertyConditionType.NEW);
        propertyAdvertFormData1.setParkingType(ParkingType.STREET);
        propertyAdvertFormData1.setTitle("Nagyon szép eladó lakás a kerületben");
        propertyAdvertFormData1.setPlaceId("EipCdWRhcGVzdCwgS29zc3V0aCBMYWpvcyB0w6lyLCAxMDU1IEh1bmdhcnkiLiosChQKEgnn2j6uEdxBRxG35SIm7l77zxIUChIJuYIq9hPcQUcROei1dj8q7xo");
        propertyAdvertFormData1.setLongitude(19.0242842);
        propertyAdvertFormData1.setLatitude(47.5254524);
        propertyAdvertFormData1.setAddress("Szeged, Pusztaszeri út, Magyarország");
        propertyAdvertFormData1.setCity("Szeged");
        propertyAdvertFormData1.setDistrict("XI. kerület");
        propertyAdvertFormData1.setStreet("Pusztaszeri út");
        propertyAdvertFormData1.setArea(85);
        propertyAdvertFormData1.setNumberOfRooms(6);
        propertyAdvertFormData1.setElevator(true);
        propertyAdvertFormData1.setBalcony(false);
        propertyAdvertFormData1.setDescription("A lakás 50 m2-es két szobás és erkélyes. A szobák laminált padlósak, a többi helyiség járólapos, nyílászárók cseréltek A lakás közös zárt folyosóról közelíthető meg, egy emeleten 4lakás, egy folyoson 2 lakás található.");

        PropertyAdvert propertyAdvert = new PropertyAdvert(propertyAdvertFormData, user);
        advertRepository.save(propertyAdvert);

        PropertyAdvert propertyAdvert1 = new PropertyAdvert(propertyAdvertFormData1, user1);
        advertRepository.save(propertyAdvert1);

        BidFormData bidFormData = new BidFormData();
        bidFormData.setAmountOfBid(300.0);

        BidFormData bidFormData1 = new BidFormData();
        bidFormData1.setAmountOfBid(600.0);

        Bid bid = new Bid(propertyAdvert, user, bidFormData);
        bidRepository.save(bid);

        Bid bid1 = new Bid(propertyAdvert1, user1, bidFormData1);
        bidRepository.save(bid1);
    }

    @Test
    public void testFindBidsByPropertyAdvertIdTwoHits() {
        List<BidListItem> resultList = bidRepository.findBidsByPropertyAdvertId(1l).
                map(BidListItem::new).collect(Collectors.toList());
        assertEquals(2, resultList.size());
    }

    @Test
    public void TestFindNumberOfUniqueBiddersTwoHits() {
        Long result = bidRepository.findNumberOfUniqueBidders(1l);
        Long expected = 2l;
        assertEquals(expected, result);
    }


}
