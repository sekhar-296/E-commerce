package com.niit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.domain.Cart;
import com.niit.domain.Product;
import com.niit.exception.ProductNotFoundException;
import com.niit.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v5/")
public class CartController {
    private ResponseEntity responseEntity;
    private ProductService service;


    @Autowired
    public CartController(ProductService service){
        this.service = service;
    }

    @PostMapping("/product")
    public ResponseEntity addProductToCart(@RequestBody Cart cart) throws Exception {
        System.out.println(cart);
        responseEntity=new ResponseEntity(service.saveProduct(cart), HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/products/{email}")
    public ResponseEntity showAllProductInCart(@PathVariable String email){
        try{
            responseEntity=new ResponseEntity(service.getAllProducts(email),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseEntity;
    }

    @DeleteMapping("/delete/{productId}/{buyerEmail}")
    public ResponseEntity<?> deleteProductByProductId(@PathVariable("productId") String productId,@PathVariable String buyerEmail)throws ProductNotFoundException{
        try {
            service.deleteAProduct(buyerEmail,productId);
            responseEntity = new ResponseEntity("Successfully deleted !!!", HttpStatus.OK);
        } catch (ProductNotFoundException e) {

            throw new ProductNotFoundException();

        }
        catch (Exception exception){
            String ex = exception.getMessage();
            System.out.println(exception.getMessage());
            responseEntity = new ResponseEntity("Error !!! Try after sometime.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("/deleteAll/{buyerEmail}")
    public ResponseEntity<?> deleteAllProducts(@PathVariable String buyerEmail)throws ProductNotFoundException{
        try{
            responseEntity=new ResponseEntity(service.deleteAllProducts(buyerEmail),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseEntity;
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity getProductById(@PathVariable ("productId") String productId) throws ProductNotFoundException {
//        try{
        responseEntity=new ResponseEntity(service.getProductById(productId),HttpStatus.OK);
//        }catch (ProductNotFoundException e){
//            throw new ProductNotFoundException();
//        }
        return responseEntity;

    }

    @GetMapping("/email/{buyerEmail}")
    public ResponseEntity getProductByEmail(@PathVariable String buyerEmail){
        responseEntity = new ResponseEntity(service.getCartByEmail(buyerEmail),HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("/update/{productId}/{qty}/{email}")
    public ResponseEntity<?> updateProductWithProductId(@PathVariable String productId,@PathVariable int qty,@PathVariable String email)throws ProductNotFoundException{
        return new ResponseEntity(service.updateAProduct(email,productId,qty), HttpStatus.OK);
    }
    @PostMapping(value = "/showlist/{email}", consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE })
    public void showList( @RequestPart("product") String productList, @PathVariable String email){

        String [] products = productList.split("},");
        List<Cart> list = new ArrayList<>();
        for (String str: products) {
            try {
                list.add(getProduct(str+"}"));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        //System.out.println(file.size() + "  " + list);
    }

    private Cart getProduct(String productString) throws JsonProcessingException {
        return new ObjectMapper().readValue(productString, Cart.class);
    }

}
