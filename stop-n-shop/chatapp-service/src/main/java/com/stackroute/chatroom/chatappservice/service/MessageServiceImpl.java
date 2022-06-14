package com.stackroute.chatroom.chatappservice.service;

import com.stackroute.chatroom.chatappservice.models.Message;
import com.stackroute.chatroom.chatappservice.repository.MessageRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    private MessageRepository messageRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> findByFrom(String id) {
        return messageRepository.findByFrom(id);
    }

    @Override
    public List<?> findAllMessages(String from, String to) {
        return messageRepository.findChat(from,to);
    }


}
