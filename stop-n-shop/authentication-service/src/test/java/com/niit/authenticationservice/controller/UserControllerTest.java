package com.niit.authenticationservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.authenticationservice.domain.Address;
import com.niit.authenticationservice.domain.Role;
import com.niit.authenticationservice.domain.User;
import com.niit.authenticationservice.exception.UserAlreadyExistsException;
import com.niit.authenticationservice.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.persistence.Embedded;
import javax.persistence.Id;
import java.util.Arrays;
import java.util.List;

import static com.niit.authenticationservice.domain.Role.BUYER;
import static com.niit.authenticationservice.domain.Role.SELLER;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;
    private User user1, user2;
    private Address address1 , address2;
    List<User> userList;


    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp(){

        address1 = new Address("streetName1","city1","landMark1","state1",
                "pin1","nationality1");
        user1 = new User("Ajith","ajithnytr@gmail.com","9852412563","22-02-20",
                "Ajith@145",SELLER,address1);
        address2 = new Address("streetName2","city2","landMark2","state2",
                "pin2","nationality2");
        user2 = new User("Arjun","arjun@gmail.com","9852412585","28-02-25",
                "Arjun@123",BUYER,address2);
        userList = Arrays.asList(user1,user2);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

    }
    @AfterEach
    public void tearDown(){
        user1=null;
        user2 = null;
    }
    @Test
    public void givenUserToSaveReturnSaveUserSuccess() throws Exception {
        when(userService.saveUser(any())).thenReturn(user1);
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(user1)))
                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
     //   verify(userService,times(1)).saveUser(any());

    }
    @Test
    public void givenCustomerToSaveReturnSaveProductFailure() throws Exception {
        when(userService.saveUser(any())).thenThrow(UserAlreadyExistsException.class);
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(user1)))
                .andExpect(status().isBadRequest()).andDo(MockMvcResultHandlers.print());
 //       verify(userService,times(1)).saveUser(user1);

    }
    private static String jsonToString(final Object ob) throws JsonProcessingException {
        String result;

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(ob);
            System.out.println("Json Content that has been posted:\n"+jsonContent);
            result = jsonContent;
        } catch(JsonProcessingException e) {
            result = "JSON processing error";
        }

        return result;
    }

}
