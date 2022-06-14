package com.niit.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.domain.Product;

import com.niit.exception.CategoryNotFoundException;
import com.niit.exception.CityNotFoundException;
import com.niit.exception.EmptyInputFieldException;
import com.niit.exception.ProductNotFoundException;

import com.niit.repository.RecommendationRepository;
import com.niit.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * Controller to serve recommendations
 */
@RestController
@Validated
//@CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping("/api/v1/")
@RequestMapping("/api/v9/")
public class RecommendationController {
    private ResponseEntity responseEntity;
    private RecommendationService recommendationService;
    private Neo4jClient neo4jClient;



    @Autowired
    public RecommendationController(RecommendationService service,Neo4jClient neo4jClient){
        this.recommendationService=service;
        this.neo4jClient =neo4jClient;
    }




    @PostMapping("/product")
    public ResponseEntity addProduct( @RequestParam("file") MultipartFile file,  @RequestParam ("Product") String product) throws Exception , EmptyInputFieldException {


            ObjectMapper objectMapper=new ObjectMapper();
            Product product2 = objectMapper.readValue(product,Product.class);
            product2.setProductImage(file.getBytes());
            Product product1 = recommendationService.saveproduct(product2);
//            responseEntity = new ResponseEntity(recommendationService.getProductByProductCategory(product2.getProductCategory()), HttpStatus.OK);

            //        responseEntity = new ResponseEntity(recommendationService.getProductByCity(p.getCity()), HttpStatus.OK);

            return responseEntity = new ResponseEntity(product1, HttpStatus.CREATED);


    }

    @GetMapping("/products")
    public ResponseEntity getAllProducts(@Valid HttpServletRequest request) throws Exception {

        List<Product> list =  recommendationService.getAllProducts();
        try {
            responseEntity = new ResponseEntity(list, HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return responseEntity;

    }

    @GetMapping("/product/{id}")
    public ResponseEntity getProductById(@PathVariable String id) throws ProductNotFoundException {
        try{
            responseEntity=new ResponseEntity(recommendationService.getProductById(id),HttpStatus.OK);
        }catch (ProductNotFoundException e){
            throw new ProductNotFoundException();
        }
        return responseEntity;

    }

    @GetMapping(value = "/city/{city}")
    public ResponseEntity getAllProductBycity( @PathVariable  String city) throws ProductNotFoundException, CityNotFoundException {

        Product product = new Product();

//        try{
//
            responseEntity = new ResponseEntity(recommendationService.getProductByCity(city), HttpStatus.OK);
//
//        }catch (Exception e){
//            responseEntity = new ResponseEntity("Error !!! Try  sdfsfdsdfsafter sometime.", HttpStatus.INTERNAL_SERVER_ERROR);
//        }

        return responseEntity;
    }

    @GetMapping(value = "/category/{productCategory}")
    public ResponseEntity getAllProductByCategory(@PathVariable String productCategory) throws CategoryNotFoundException {



//        try{
//
        responseEntity = new ResponseEntity(recommendationService.getProductByProductCategory(productCategory), HttpStatus.OK);
//
//        }catch (Exception e){
//            responseEntity = new ResponseEntity("Error !!! Try  after sometime.", HttpStatus.INTERNAL_SERVER_ERROR);
//        }

        return responseEntity;
    }

    @GetMapping("product/{category}/{city}")
    public ResponseEntity getProductByCityAndCategory(@PathVariable String category,@PathVariable String city){
        responseEntity = new ResponseEntity(recommendationService.getByCityCategory(category,city),HttpStatus.OK);
    return responseEntity;
    }

    @PatchMapping("/updateProduct/{productId}/{productQuantity}")
    public ResponseEntity<?> updateProductWithProductId(@PathVariable String productId , @PathVariable int productQuantity)throws ProductNotFoundException{

        try{
            responseEntity=new ResponseEntity(recommendationService.updateProductWithProductId(productId,productQuantity),HttpStatus.OK);
        }catch (ProductNotFoundException e){
            throw new ProductNotFoundException();
        }
        return responseEntity;
    }

    @DeleteMapping(value = "/{id}")
    public boolean deleteProduct(@PathVariable String id){
        try{

            responseEntity = new ResponseEntity(recommendationService.deleteProduct(id), HttpStatus.OK);

        }catch (Exception e){
            responseEntity = new ResponseEntity("Error !!! Try after sometime.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return true;

    }
    }






//    @GetMapping(value = "/product{category}")
//    public List<Product> getAllProductsByCategory(@PathVariable String category) {
//
//        List<Product>products = null;
//        try {
//            products= recommendationService.getAllProductBycategory(category);
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//        return products;
//
//    }
//@GetMapping(value = "/recommend/{productname}")
//public ResponseEntity<?> recommend(@PathVariable String productName) {
//    try {
//        return new ResponseEntity<>(recommendationService.fetchRecommendation(productName), HttpStatus.OK);
//    } catch (Exception e) {
//        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
//}




/** method to fetch the details of product based on product name
    @GetMapping(value = "product{productname}")
    public ProductDetails getDetails(@PathVariable String name){

    }

 **/


