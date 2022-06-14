package com.stackroute.chatroom.chatappservice.service;

import java.util.List;

import com.stackroute.chatroom.chatappservice.models.Message;
import com.stackroute.chatroom.chatappservice.models.User;

public interface UserService {
    public User saveData(User user);

    // public List<User> saveMessage(User message);
    // public List<Message> findMessages(String senderId, String recipientId);

    // public Message findChatById(String id) throws ResourceNotFoundException;

}
