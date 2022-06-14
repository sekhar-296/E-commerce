package com.stackroute.paymentgateway.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String productId;
    private String productName;
    private String productImage;
    private BigDecimal productPrice;
    private int productQuantity;
    private String brand;
    private BigDecimal sellingPrice;
    private String sellerEmail;
}
