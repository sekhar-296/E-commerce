package com.stackroute.niit.emailservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.niit.emailservice.controller.EmailController;
import com.stackroute.niit.emailservice.model.Email;
import com.stackroute.niit.emailservice.service.EmailService;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//

@ExtendWith(MockitoExtension.class)
public class EmailControllerTest {
    @Mock
    private EmailService emailService;
    @InjectMocks
    private EmailController emailController;

    @Autowired
    private MockMvc mockMvc;
    private Email email;
    @BeforeEach
    public void setup()
    {
        email = new Email("stopnshopcompany@gmail.com","Project@1","deep131997@gmail.com","discount","discount related");
        mockMvc = MockMvcBuilders.standaloneSetup(emailController).build();
    }
    @AfterEach
    public void destroy()
    {
        email=null;
    }
@Test
    public void givenEmailToAddSuccess() throws Exception {
    when(emailService.sendMail(any())).thenReturn(true);
    mockMvc.perform(
            post("/email")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(convertToJSON(email)))
                    .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
          verify(emailService, times(1)).sendMail(any());



}
public static String convertToJSON(final Object obj)
{
    String result="";
try
{
    ObjectMapper mapper =new ObjectMapper();
    result =mapper.writeValueAsString(obj);

}
catch(JsonProcessingException ex)
    {
ex.printStackTrace();
result="JsonProcessingException";
    }
return result;
}
}
