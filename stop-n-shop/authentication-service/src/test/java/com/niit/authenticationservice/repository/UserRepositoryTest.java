//package com.niit.authenticationservice.repository;
//
//import com.niit.authenticationservice.domain.Address;
//import com.niit.authenticationservice.domain.User;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.niit.authenticationservice.domain.Role.SELLER;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//@ActiveProfiles("test")
//
//public class UserRepositoryTest {
//
//    @Autowired
//    private UserRepository userRepository;
//    private Address address;
//    private User user;
//
//
//    @BeforeEach
//    public void setUp(){
//        System.out.println("Test case running");
//        address = new Address("streetName1","city1","landMark1","state1",
//                "pin1","nationality1");
//
//        user = new User("Ajith","ajith@gmail.com","9852412563","22-02-20",
//                "Ajith@145",SELLER,address);
//    }
//    @AfterEach
//    public void tearDown(){
//
//        address = null;
//        user = null;
//        userRepository.deleteAll();
//        System.out.println("test case stopping");
//    }
//
//    @Test
//    public void givenUserToSaveShouldReturnUser(){
//
//
//        User user1 = userRepository.save(user);
//
//        assertNotNull(user);
//
//        assertEquals(user.getEmail(),user1.getEmail());
//    }
//
//    @Test
//    public void givenTrackReturnGetAllTrack(){
//
//
//         Address address1 = new Address("streetName1","city1","landMark1","state1",
//                "pin1","nationality1");
//        User    user1 = new User("Arjun","ajith1@gmail.com","9852412563","22-02-20",
//                "Ajith@145",SELLER,address1);
//         List<User> list=new ArrayList<>();
//         list.add(user1);
//
//        list = userRepository.findAll();
//        assertEquals(2,list.size());
//        assertEquals("Arjun",list.get(2).getUserName());
//
//    }
//
//}
