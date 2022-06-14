package com.stackroute.niit.emailservice.service;

import com.stackroute.niit.emailservice.model.Email;

import javax.mail.MessagingException;

public interface EmailService {
    public boolean sendMail(Email email) throws MessagingException;

}
