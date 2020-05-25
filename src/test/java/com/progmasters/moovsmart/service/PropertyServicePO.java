package com.progmasters.moovsmart.service;

import com.progmasters.moovsmart.domain.ParkingType;
import com.progmasters.moovsmart.domain.PropertyAdvert;
import com.progmasters.moovsmart.domain.PropertyConditionType;
import com.progmasters.moovsmart.domain.PropertyType;
import com.progmasters.moovsmart.domain.user.User;
import com.progmasters.moovsmart.domain.user.UserIdentifier;
import com.progmasters.moovsmart.domain.user.UserRole;
import com.progmasters.moovsmart.dto.form.PropertyAdvertFormData;
import com.progmasters.moovsmart.dto.list.PropertyAdvertListItem;
import com.progmasters.moovsmart.repository.AdvertRepository;
import com.progmasters.moovsmart.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.*;

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class PropertyServicePO {

    private PropertyAdvertService propertyAdvertService;

    @Mock
    private AdvertRepository advertRepository;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        propertyAdvertService = new PropertyAdvertService(advertRepository, userRepository);
    }

    @Test
    void testSavePropertyAdvert() {
        PropertyAdvertFormData propertyAdvertFormData = mock(PropertyAdvertFormData.class);
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

        User user = mock(User.class);
        user.setUserName("user");
        user.setEmail("akarmi@akarmi.com");
        user.setPasswordHash("1234");
        user.setUserRoles(Collections.singletonList(UserRole.ROLE_USER));

        UserIdentifier userIdentifier = UserIdentifier.forUser(user);



    }


}
