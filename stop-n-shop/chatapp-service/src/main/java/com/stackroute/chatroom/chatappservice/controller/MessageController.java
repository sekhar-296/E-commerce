package com.stackroute.chatroom.chatappservice.controller;

import java.util.List;

import com.stackroute.chatroom.chatappservice.models.Message;
import com.stackroute.chatroom.chatappservice.models.User;
import com.stackroute.chatroom.chatappservice.repository.UserRepository;
import com.stackroute.chatroom.chatappservice.service.MessageServiceImpl;
import com.stackroute.chatroom.chatappservice.service.UserService;
import com.stackroute.chatroom.chatappservice.service.UserServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
// @RequestMapping("/app/v1")
//@RequestMapping("/api/v2/")
public class MessageController {

        private UserServiceImpl userServiceImpl;
        private MessageServiceImpl messageServiceImpl;
        private UserRepository messageRepository;

        @Autowired
        public MessageController(UserServiceImpl userServiceImpl, UserRepository messageRepository,
                        MessageServiceImpl messageServiceImpl) {
                this.userServiceImpl = userServiceImpl;
                this.messageRepository = messageRepository;
                this.messageServiceImpl = messageServiceImpl;
        }

        @GetMapping("getusermessage/{from}/{to}")
        public List<?> findMessageByUser(@PathVariable String from, @PathVariable String to){
            return messageServiceImpl.findAllMessages(from,to);
        }

        @PostMapping("messages")
        public User loginUser(@RequestBody User user) {
                return (userServiceImpl.saveData(user));
        }

        @GetMapping("users")
        public List<User> getUsers() {
                return messageRepository.findAll();
        }

        @GetMapping("/{from}")
        public ResponseEntity<?> findMessages(@PathVariable String from) {
                return new ResponseEntity<>(messageServiceImpl.findByFrom(from), HttpStatus.OK);
        }

}
