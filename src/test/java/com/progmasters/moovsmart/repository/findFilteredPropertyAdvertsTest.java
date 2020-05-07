package com.progmasters.moovsmart.repository;

import com.progmasters.moovsmart.domain.ParkingType;
import com.progmasters.moovsmart.domain.PropertyAdvert;
import com.progmasters.moovsmart.domain.PropertyConditionType;
import com.progmasters.moovsmart.domain.PropertyType;
import com.progmasters.moovsmart.domain.user.User;
import com.progmasters.moovsmart.domain.user.UserRole;
import com.progmasters.moovsmart.dto.form.PropertyAdvertFormData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class findFilteredPropertyAdvertsTest {

    @Autowired
    private AdvertRepository advertRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    public void createUser() {
        User user = new User("akarmi@akarmi.com", "user", "1234", UserRole.ROLE_USER);
        userRepository.save(user);
        User user1 = new User("akarmi1@akarmi1.com", "user1", "2345", UserRole.ROLE_USER);
        userRepository.save(user1);
    }

    @Test
    public void testFullSearch() {
        PropertyAdvertFormData propertyAdvertFormData = new PropertyAdvertFormData();
        propertyAdvertFormData.setPrice(20.0);
        propertyAdvertFormData.setId(300000);
        propertyAdvertFormData.setPropertyType(PropertyType.FLAT);
        propertyAdvertFormData.setPropertyConditionType(PropertyConditionType.NEW);
        propertyAdvertFormData.setParkingType(ParkingType.STREET);
        propertyAdvertFormData.setTitle("Nagyon szép eladó lakás a kerületben");
        propertyAdvertFormData.setPlaceId("EipCdWRhcGVzdCwgS29zc3V0aCBMYWpvcyB0w6lyLCAxMDU1IEh1bmdhcnkiLiosChQKEgnn2j6uEdxBRxG35SIm7l77zxIUChIJuYIq9hPcQUcROei1dj8q7xo");
        propertyAdvertFormData.setLongitude(19.0242842);
        propertyAdvertFormData.setLatitude(47.5254524);
        propertyAdvertFormData.setAddress("Budapest, Pusztaszeri út, Magyarország");
        propertyAdvertFormData.setCity("Budapest");
        propertyAdvertFormData.setDistrict("XI. kerület");
        propertyAdvertFormData.setStreet("Pusztaszeri út");
        propertyAdvertFormData.setArea(45);
        propertyAdvertFormData.setNumberOfRooms(3);
        propertyAdvertFormData.setElevator(true);
        propertyAdvertFormData.setBalcony(false);
        propertyAdvertFormData.setDescription("A lakás 50 m2-es két szobás és erkélyes. A szobák laminált padlósak, a többi helyiség járólapos, nyílászárók cseréltek A lakás közös zárt folyosóról közelíthető meg, egy emeleten 4lakás, egy folyoson 2 lakás található.");

        Optional<User> tempUser = userRepository.findOneByUserName("user");
        if (tempUser.isPresent()) {
            User actualUser = tempUser.get();
            PropertyAdvert propertyAdvert = new PropertyAdvert(propertyAdvertFormData, actualUser);
            advertRepository.save(propertyAdvert);
        }





    }


}
