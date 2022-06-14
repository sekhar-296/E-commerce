package com.niit.domain;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Document
public class Cart {
    private List<Product> productList;
    @Id
    private String buyerEmail;

    public List<Product> getProductList(){
        return this.productList;
    }
}
