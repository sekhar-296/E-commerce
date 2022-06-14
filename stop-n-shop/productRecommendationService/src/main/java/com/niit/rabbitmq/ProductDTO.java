package com.niit.rabbitmq;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class ProductDTO {
    private String productId;
    private String productName;
    private BigDecimal mrp;
    private BigDecimal sellingPrice;
    private byte[] productImage;
    private String productCategory;
    private String desription;
    private String brand;
    private int productQuantity;
    private String city;
//    private long buyerPin ;
//    private long sellerPin;
}
