package com.niit.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Document
public class Product {
    @Id
    private String productId;
    private String productName;
    private String productImage;
    private BigDecimal productPrice;
    private int productQuantity;
    private String brand;
    private BigDecimal sellingPrice;
    private String sellerEmail;


}
