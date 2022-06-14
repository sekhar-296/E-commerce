package com.stackroute.productownerservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;

@Document(indexName = "my-products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    @Id
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
    private String shopId;
    private byte[] qrcode;
}
