package com.stackroute.chatroom.chatappservice.models;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Message {
    private String message;
    private String from;
    private String to;
    private Date timestamp;
}
