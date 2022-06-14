package com.stackroute.chatroom.chatappservice.repository;

import com.stackroute.chatroom.chatappservice.models.Message;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MessageRepository extends MongoRepository<Message, String> {
    @Query("{$or:[{'from' : ?0,'to':?1},{'from' : ?1,'to':?0}]}")
    public List<?> findChat(String fromId,String toId);
    public abstract List<Message> findByFrom(String from);
}
