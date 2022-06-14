package com.stackroute.rabbitmq.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO {
    private String productId;
    private String productName;
    private BigDecimal mrp;
    private BigDecimal sellingPrice;
    private byte[] productImage;
    private String productCategory;
    private String description;
    private String brand;
    private int productQuantity;
    private String sellerEmail;
    private String sellerCity;
}
