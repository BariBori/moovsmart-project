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
public class findBidsByPropertyAdvertIdTest {

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

        PropertyAdvert propertyAdvert = new PropertyAdvert(propertyAdvertFormData, user);
        advertRepository.save(propertyAdvert);

        BidFormData bidFormData = new BidFormData();
        bidFormData.setAmountOfBid(300.0);

        Bid bid = new Bid(propertyAdvert, user, bidFormData);
        bidRepository.save(bid);

    }

    @Test
    public void testFindBidsByPropertyAdvertIdOneHit() {
        List<BidListItem> resultList = bidRepository.findBidsByPropertyAdvertId(1l).
                map(BidListItem::new).collect(Collectors.toList());
        assertEquals(1, resultList.size());
    }



}
