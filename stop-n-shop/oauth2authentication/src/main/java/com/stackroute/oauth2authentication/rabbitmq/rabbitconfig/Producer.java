package com.stackroute.oauth2authentication.rabbitmq.rabbitconfig;

import com.stackroute.oauth2authentication.rabbitmq.domain.UserDTO;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private RabbitTemplate rabbitTemplate;
    private DirectExchange directExchange;

    @Autowired
    public Producer(RabbitTemplate rabbitTemplate, DirectExchange directExchange) {
        super();
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
    }
    public void sendMessageToRabbitMq(UserDTO userDTO)
    {
        rabbitTemplate.convertAndSend(directExchange.getName(),"googleuser_routing",userDTO);
    }
}
