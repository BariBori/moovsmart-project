package com.progmasters.moovsmart.repository.message;

import com.progmasters.moovsmart.domain.ParkingType;
import com.progmasters.moovsmart.domain.PropertyAdvert;
import com.progmasters.moovsmart.domain.PropertyConditionType;
import com.progmasters.moovsmart.domain.PropertyType;
import com.progmasters.moovsmart.domain.messaging.Chat;
import com.progmasters.moovsmart.domain.user.User;
import com.progmasters.moovsmart.domain.user.UserRole;
import com.progmasters.moovsmart.dto.form.PropertyAdvertFormData;
import com.progmasters.moovsmart.repository.AdvertRepository;
import com.progmasters.moovsmart.repository.UserRepository;
import com.progmasters.moovsmart.repository.messaging.ChatRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ChatRepositoryTest {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdvertRepository advertRepository;

    @BeforeEach
    void createChat() {
        User user = new User("akarmi@akarmi.com", "user", "1234", UserRole.ROLE_USER);
        userRepository.save(user);

        PropertyAdvertFormData propertyAdvertFormData = new PropertyAdvertFormData();
        propertyAdvertFormData.setPrice(40.0);
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

        Chat chat = new Chat(user, propertyAdvert);
        chatRepository.save(chat);
    }

    @Test
    void testSaveChatWithOneHit() {
        List<Chat> chatList = chatRepository.findAll();
        assertEquals(1, chatList.size());
    }

//    @Test
//    void testFindOneByEnquirer_IdAndAdvert_IdWithOneHit() {
//        Optional<Chat> optChat = chatRepository.findOneByEnquirer_IdAndAdvert_Id(1L, 1L);
//        List<Chat> chatList = new ArrayList<>();
//        if(optChat.isPresent()) {
//            Chat chat = optChat.get();
//            chatList.add(chat);
//        }
//        assertEquals(1, chatList.size());
//    }

    @Test
    void testFindOneByEnquirer_IdAndAdvert_IdWithWrongUserId() {
        Optional<Chat> optChat = chatRepository.findOneByEnquirer_IdAndAdvert_Id(2L, 1L);
        List<Chat> chatList = new ArrayList<>();
        if(optChat.isPresent()) {
            Chat chat = optChat.get();
            chatList.add(chat);
        }
        assertEquals(0, chatList.size());
    }

    @Test
    void testFindOneByEnquirer_IdAndAdvert_IdWithWrongAdvertId() {
        Optional<Chat> optChat = chatRepository.findOneByEnquirer_IdAndAdvert_Id(1L, 2L);
        List<Chat> chatList = new ArrayList<>();
        if(optChat.isPresent()) {
            Chat chat = optChat.get();
            chatList.add(chat);
        }
        assertEquals(0, chatList.size());
    }

    @Test
    void testFindOneByEnquirer_IdAndAdvert_IdWithWrongIds() {
        Optional<Chat> optChat = chatRepository.findOneByEnquirer_IdAndAdvert_Id(5L, 2L);
        List<Chat> chatList = new ArrayList<>();
        if(optChat.isPresent()) {
            Chat chat = optChat.get();
            chatList.add(chat);
        }
        assertEquals(0, chatList.size());
    }


}
