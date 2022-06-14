package com.stackroute.paymentgateway.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Orders {
    @Id
    private String orderId;
    private int amount;
    private String Currency;
    private int amountPaid;
    private int amountDue;
    private String receipt;
    private String status;
    private String name;
    private String emailId;
    private String paymentId;
    private List<Product> producList;

}
