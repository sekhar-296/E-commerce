package com.stackroute.chatroom.chatappservice.service;

import com.stackroute.chatroom.chatappservice.models.Message;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface MessageService {
    public Message saveMessage(Message message);

    public List<Message> findByFrom(String from);
    List<?> findAllMessages(String from, String to);
}
