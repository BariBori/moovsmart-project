//package com.progmasters.moovsmart.service;
//
//import com.progmasters.moovsmart.domain.user.User;
//import com.progmasters.moovsmart.domain.user.UserIdentifier;
//import com.progmasters.moovsmart.domain.user.UserRole;
//import com.progmasters.moovsmart.dto.user.UserDto;
//import com.progmasters.moovsmart.dto.user.UserForm;
//import com.progmasters.moovsmart.repository.UserRepository;
//import com.progmasters.moovsmart.service.user.UserService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//import static org.junit.Assert.assertEquals;
//
//@ExtendWith(MockitoExtension.class)
//class UserServiceTest {
//
//    private UserService userService;
//
//    @Mock
//    private UserRepository userRepository;
//
//    @BeforeEach
//    void setup() {
//        userService = new UserService(userRepository);
//    }
//
//    @Test
//    void testRegisterUser() {
//
//        UserForm mock = new UserForm(){
//
//            public String getEmail() {
//                return "foo";
//            }
//
//            public String getPassword() {
//                return "bar";
//            }
//
//            public String getUserName() {
//                return "baz";
//            }
//        };
//
//        userService.registerUser(mock);
//
//
//    }
//
//}
