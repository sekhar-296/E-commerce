//package com.stackroute.niit.emailservice;
//
//import com.stackroute.niit.emailservice.controller.EmailController;
//import com.stackroute.niit.emailservice.model.Email;
//import com.stackroute.niit.emailservice.service.EmailService;
//import com.stackroute.niit.emailservice.service.EmailServiceImpl;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import javax.mail.MessagingException;
//
//import java.util.Optional;
//
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class EmailServiceTest {
// @Autowired
//    private EmailService emailService;
//    private Email email;
//    @BeforeEach
//    public void setup() {
//        email = new Email("stopnshopcompany@gmail.com", "Project@1", "deep131997@gmail.com", "discount", "discount related");
//    }
//@AfterEach
//public void destroy()
//{
//    email=null;
//}
//
//    @Test
//    public void givenEmailToSaveReturnEmail() throws MessagingException
//    {
//        emailService.sendMail(email);
//    }
//
//}
