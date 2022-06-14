package com.stackroute.chatroom.chatappservice.controller;

import com.stackroute.chatroom.chatappservice.models.Message;
import com.stackroute.chatroom.chatappservice.service.MessageServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/v2/")
public class ChatController {
    private MessageServiceImpl messageServiceImpl;
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    public ChatController(MessageServiceImpl messageServiceImpl) {
        this.messageServiceImpl = messageServiceImpl;
    }

    @MessageMapping("message")
    public void processMessage(@Payload Message message) {
        messageServiceImpl.saveMessage(message);
        messagingTemplate.convertAndSend("/topic/" + message.getTo(), message);
    }

}
