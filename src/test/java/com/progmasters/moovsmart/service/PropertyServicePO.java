package com.progmasters.moovsmart.service;
import com.progmasters.moovsmart.domain.ParkingType;
import com.progmasters.moovsmart.domain.PropertyAdvert;
import com.progmasters.moovsmart.domain.PropertyConditionType;
import com.progmasters.moovsmart.domain.PropertyType;
import com.progmasters.moovsmart.domain.user.User;
import com.progmasters.moovsmart.domain.user.UserIdentifier;
import com.progmasters.moovsmart.domain.user.UserRole;
import com.progmasters.moovsmart.dto.form.PropertyAdvertFormData;
import com.progmasters.moovsmart.dto.form.PropertyEditForm;
import com.progmasters.moovsmart.repository.AdvertRepository;
import com.progmasters.moovsmart.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PropertyServicePO {

    private PropertyAdvertService advertService;

    @Mock
    private AdvertRepository advertRepositoryMock;

    @Mock
    private UserRepository userRepositoryMock;

    @BeforeEach
    void setUp() {
        advertService = new PropertyAdvertService(advertRepositoryMock, userRepositoryMock);
    }

    @Test
    void testSaveAdvertWithValidUser() {
        User user = new User("akarmi@akarmi.com", "user", "1234", UserRole.ROLE_USER);
        userRepositoryMock.save(user);

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

        UserIdentifier userIdentifier = new UserIdentifier();
        UserIdentifier.forUser(user);

        advertService.saveAdvert(propertyAdvertFormData, userIdentifier);

        assertEquals(1, advertService.listPropertyAdverts().size());


    }

    @Test
    void testUpdatePropertyAdvert() {
        PropertyAdvertFormData originalPropertyAdvertData = new PropertyAdvertFormData();
        originalPropertyAdvertData.setPrice(20.0);
        originalPropertyAdvertData.setId(300000);
        originalPropertyAdvertData.setPropertyType(PropertyType.FLAT);
        originalPropertyAdvertData.setPropertyConditionType(PropertyConditionType.NEW);
        originalPropertyAdvertData.setParkingType(ParkingType.STREET);
        originalPropertyAdvertData.setTitle("Nagyon szép eladó lakás a kerületben");
        originalPropertyAdvertData.setPlaceId("EipCdWRhcGVzdCwgS29zc3V0aCBMYWpvcyB0w6lyLCAxMDU1IEh1bmdhcnkiLiosChQKEgnn2j6uEdxBRxG35SIm7l77zxIUChIJuYIq9hPcQUcROei1dj8q7xo");
        originalPropertyAdvertData.setLongitude(19.0242842);
        originalPropertyAdvertData.setLatitude(47.5254524);
        originalPropertyAdvertData.setAddress("Budapest, Pusztaszeri út, Magyarország");
        originalPropertyAdvertData.setCity("Budapest");
        originalPropertyAdvertData.setDistrict("XI. kerület");
        originalPropertyAdvertData.setStreet("Pusztaszeri út");
        originalPropertyAdvertData.setArea(45);
        originalPropertyAdvertData.setNumberOfRooms(3);
        originalPropertyAdvertData.setElevator(true);
        originalPropertyAdvertData.setBalcony(false);
        originalPropertyAdvertData.setDescription("A lakás 50 m2-es két szobás és erkélyes. A szobák laminált padlósak, a többi helyiség járólapos, nyílászárók cseréltek A lakás közös zárt folyosóról közelíthető meg, egy emeleten 4lakás, egy folyoson 2 lakás található.");

        User user = new User("akarmi@akarmi.com", "user", "1234", UserRole.ROLE_USER);

        PropertyAdvert originalPropertyAdvert = new PropertyAdvert(originalPropertyAdvertData, user);

        PropertyEditForm propertyEditForm = new PropertyEditForm();
        propertyEditForm.setArea(97);
        propertyEditForm.setNumberOfRooms(5);
        propertyEditForm.setPrice(71.5);

        when(advertRepositoryMock.findById(1L)).thenReturn(Optional.of(originalPropertyAdvert));
        when(advertRepositoryMock.save(any(PropertyAdvert.class))).thenAnswer(returnsFirstArg());

        boolean updatedAdvert = advertService.updateProperty(propertyEditForm, 1L);
        assertTrue(updatedAdvert);
    }


}
