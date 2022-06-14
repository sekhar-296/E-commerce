package com.niit.domain;

import lombok.*;
//import javax.persistence.GeneratedValue;


import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.annotation.Id;
//import javax.persistence.GeneratedValue;

import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Generated;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

@Node
public class Product {
    @Id
    private String productId;
    @NotNull(message = "Product Name is mandatory")
    private String productName;
    @NotNull(message = "mrp Name is mandatory")
    private BigDecimal mrp;
    @NotNull(message = "sellingPrice Name is mandatory")
    private BigDecimal sellingPrice;
    @NotNull(message = "productImage Name is mandatory")
    private byte[] productImage;
    @NotNull(message = "productCategory Name is mandatory")
    private String productCategory;
    @NotNull(message = "desription Name is mandatory")
    private String desription;
    @NotNull(message = "brand Name is mandatory")
    private String brand;
    @NotNull(message = "productQuantity Name is mandatory")
//    @Size(min = 1)
    private int productQuantity;
    @NotNull(  message = "City Name is mandatory")
//    @Size(min = 10,max = 15,message = "city should be <15 and >10")
    private String city;


//    @PostMapping(value = "/product", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity addProduct(@RequestBody @Valid Product product) throws Exception {
//
//
//        //ObjectMapper objectMapper=new ObjectMapper();
//        //Product product2 = objectMapper.readValue(product,Product.class);
//        //product.setProductImage(file.getBytes());
//        Product product1 = recommendationService.saveproduct(product);
//        responseEntity = new ResponseEntity(recommendationService.getProductByProductCategory(product.getProductCategory()), HttpStatus.OK);
//
//        //        responseEntity = new ResponseEntity(recommendationService.getProductByCity(p.getCity()), HttpStatus.OK);
//
//        return responseEntity = new ResponseEntity(product1, HttpStatus.CREATED);
//
//
//    }

}
