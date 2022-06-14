package com.stackroute.chatroom.chatappservice.repository;

import com.stackroute.chatroom.chatappservice.models.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    // User save(User user);

    // long countBySenderIdAndRecipientIdAndStatus(
    // String senderId, String recipientId, MessageStatus status);

    // List<Message> findByChatId(String chatId);
}
