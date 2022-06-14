package com.stackroute.chatroom.chatappservice.service;

import java.util.List;

import com.stackroute.chatroom.chatappservice.models.Message;
import com.stackroute.chatroom.chatappservice.models.User;
import com.stackroute.chatroom.chatappservice.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository messageRepository;

    @Autowired
    public UserServiceImpl(UserRepository messageRepository) {

        this.messageRepository = messageRepository;
    }

    @Override
    public User saveData(User user) {
        return messageRepository.save(user);

    }

   

}
