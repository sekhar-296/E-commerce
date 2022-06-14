package com.stackroute.niit.emailservice.controller;

import com.stackroute.niit.emailservice.model.Email;
import com.stackroute.niit.emailservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

//@CrossOrigin("*")
@RestController
@RequestMapping("/api/v4/")
public class EmailController {
    private EmailService emailService;
    @Autowired
    public EmailController(EmailService emailService) {this.emailService=emailService; }
    @PostMapping("/email")
    public ResponseEntity<?> sendMail(@RequestBody Email email) throws MessagingException {
        return new ResponseEntity<>(emailService.sendMail(email), HttpStatus.OK);
    }

}
