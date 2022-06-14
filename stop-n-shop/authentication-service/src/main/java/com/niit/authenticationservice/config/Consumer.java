package com.niit.authenticationservice.config;

import com.niit.authenticationservice.domain.Role;
import com.niit.authenticationservice.domain.User;
import com.niit.authenticationservice.exception.UserAlreadyExistsException;
import com.niit.authenticationservice.repository.UserRepository;
import com.niit.authenticationservice.service.UserServiceImpl;
import com.niit.rabbitmq.domain.UserDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    private UserServiceImpl userService;
    private UserRepository userRepository;
    @Autowired
    public Consumer(UserServiceImpl userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @RabbitListener(queues="googleuser_queue")
    public void getUserDtoFromRabbitMq(UserDTO userDto) throws UserAlreadyExistsException
    {
        User user=new User();
        user.setEmail(userDto.getEmail());
        user.setUserName(userDto.getUserName());
        user.setRole(Role.BUYER);
        if(userRepository.findById(user.getEmail()).isPresent()){
            System.out.println("User is already registered");
        }
        else{
            userService.saveUser(user);
        }
    }
}
