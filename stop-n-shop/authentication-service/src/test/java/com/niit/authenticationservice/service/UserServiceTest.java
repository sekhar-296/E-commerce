package com.niit.authenticationservice.service;

import com.niit.authenticationservice.domain.Address;
import com.niit.authenticationservice.domain.User;
import com.niit.authenticationservice.exception.UserAlreadyExistsException;
import com.niit.authenticationservice.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.niit.authenticationservice.domain.Role.BUYER;
import static com.niit.authenticationservice.domain.Role.SELLER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;
    private User user1, user2;
    List<User> userList;
    Address address1,address2;
    @BeforeEach
    public void setUp(){

        address1 = new Address("streetName1","city1","landMark1","state1",
                "pin1","nationality1");

        user1 = new User("Ajith","ajith@gmail.com","9852412563","22-02-20",
                "Ajith@145",SELLER,address1);

        address2 = new Address("streetName2","city2","landMark2","state2",
                "pin2","nationality2");

        user2 = new User("Arjun","arjun@gmail.com","9852412585","28-02-25",
                "Arjun@123",BUYER,address2);

        userList = Arrays.asList(user1,user2);
        //make mock http requests

    }
    @AfterEach
    public void tearDown(){
        user1=null;
        user2 = null;
    }
    @Test
    public void givenCustomerToSaveReturnSavedCustomerSuccess() throws UserAlreadyExistsException {

        when(userRepository.findById(user1.getEmail())).thenReturn(Optional.ofNullable(null));
        when(userRepository.save(any())).thenReturn(user1);

        assertEquals(user1,userService.saveUser(user1));

        verify(userRepository,times(1)).save(any());
        verify(userRepository,times(1)).findById(user1.getEmail());

    }

    @Test
    public void givenCustomerToSaveReturnCustomerFailure(){

        when(userRepository.findById(user1.getEmail())).thenReturn(Optional.ofNullable(user1));


        assertThrows(UserAlreadyExistsException.class,()->userService.saveUser(user1));

        verify(userRepository,times(0)).save(any());
        verify(userRepository,times(1)).findById(user1.getEmail());
    }
}
