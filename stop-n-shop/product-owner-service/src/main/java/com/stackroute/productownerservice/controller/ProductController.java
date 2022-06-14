package com.stackroute.productownerservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.WriterException;
import com.stackroute.productownerservice.exception.ProductNotFoundException;
import com.stackroute.productownerservice.model.Product;
import com.stackroute.productownerservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v8/")
public class ProductController {
    private ResponseEntity responseEntity;
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/addProduct")
    public ResponseEntity<?> addProducts(@RequestParam("Product")String product,@RequestParam("file")MultipartFile file){
        try{
            System.out.println("this is product"+product.toString());
            ObjectMapper mapper=new ObjectMapper();
            Product product1=mapper.readValue(product,Product.class);
            String pId=productService.generateProductId();
            product1.setProductId(pId);
            System.out.println("after setting id"+product1.toString());
            byte[] image=productService.generateQRCodeImage(product1.toString(),250,250);
            product1.setProductImage(file.getBytes());

            product1.setQrcode(image);
            responseEntity=new ResponseEntity(productService.addProducts(product1),HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return responseEntity;
    }

    @PatchMapping("/updateProduct/{productId}/{productQuantity}")
    public ResponseEntity<?> updateProductWithProductId(@PathVariable String productId , @PathVariable int productQuantity)throws ProductNotFoundException{

        try{
            responseEntity=new ResponseEntity(productService.updateProductWithProductId(productId,productQuantity),HttpStatus.OK);
        }catch (ProductNotFoundException e){
            throw new ProductNotFoundException();
        }
        return responseEntity;
    }
    @GetMapping("/getProduct/{productId}")
    public ResponseEntity<?> findProductWithProductId(@PathVariable String productId)throws ProductNotFoundException{
        try{
            responseEntity=new ResponseEntity(productService.findProductWithProductId(productId),HttpStatus.OK);
        }catch (ProductNotFoundException e){
            throw new ProductNotFoundException();
        }
        return responseEntity;
    }

    @GetMapping("/product/{productName}")
    public ResponseEntity<?> findProductByProductName(@PathVariable String productName) throws ProductNotFoundException{
        try{
            responseEntity=new ResponseEntity(productService.findProductsByProductName(productName),HttpStatus.OK);
        }catch (ProductNotFoundException e){
            throw new ProductNotFoundException();
        }
        return responseEntity;
    }
    @GetMapping("/products/{shopId}")
    public ResponseEntity<?> findProductByShopId(@PathVariable String shopid) throws ProductNotFoundException{
        try{
            responseEntity=new ResponseEntity(productService.findProductByShopId(shopid),HttpStatus.OK);
        }catch (ProductNotFoundException e){
            throw new ProductNotFoundException();
        }
        return responseEntity;
    }

    @GetMapping("/product/seller/{sellerEmail}")
    public ResponseEntity<?> findProductsBySeller(@PathVariable String sellerEmail)throws ProductNotFoundException{
        try{
            responseEntity=new ResponseEntity(productService.findProductBySellerEmail(sellerEmail),HttpStatus.OK);
        }catch (ProductNotFoundException e){
            throw new ProductNotFoundException();
        }
        return responseEntity;
    }
    @GetMapping("/products")
    public ResponseEntity<?> getAllProducts(){
        try{
            responseEntity=new ResponseEntity(productService.getProducts(),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseEntity;
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> deleteProductByProductId(@PathVariable String productId)throws ProductNotFoundException{
        try{
            responseEntity=new ResponseEntity(productService.deleteProductByProductId(productId),HttpStatus.OK);
        }catch (ProductNotFoundException e){
            throw new ProductNotFoundException();
        }
        return responseEntity;
    }
    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAllProducts()throws ProductNotFoundException{
        try{
            responseEntity=new ResponseEntity(productService.deleteProducts(),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseEntity;
    }
}
