package com.stackroute.shopsListService.controller;

import com.stackroute.shopsListService.model.Shop;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.shopsListService.exception.NoShopFoundException;
import com.stackroute.shopsListService.exception.ShopAlreadyExistException;
import com.stackroute.shopsListService.service.SequenceGeneratorService;
import com.stackroute.shopsListService.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
//@RequestMapping("/myapp/v1")
@RequestMapping("/api/v11/")
//@CrossOrigin("*")
public class ShopsController {

    private ResponseEntity responseEntity;
    private ShopService shopService;


    @Autowired
    public ShopsController(ShopService shopService){
        this.shopService=shopService;
        }
    @Autowired
    SequenceGeneratorService sequenceGeneratorService;



    //  http://localhost:5555/myapp/v1/shop  (POST)
    @PostMapping("/shop")
    public ResponseEntity<?> addShop(@RequestParam("Shop")String shop,@RequestParam("file") MultipartFile file) throws ShopAlreadyExistException {
        try {
            ObjectMapper mapper=new ObjectMapper();
            Shop shop1=mapper.readValue(shop,Shop.class);
            shop1.setShopImage(file.getBytes());

            responseEntity= new ResponseEntity<>(shopService.saveShop(shop1), HttpStatus.CREATED);
        }
        catch(ShopAlreadyExistException ex)
        {
            throw new ShopAlreadyExistException();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return responseEntity;
    }



   //http://localhost:5555/myapp/v1/shop  (GET)
    @GetMapping("/shop")
    public ResponseEntity<?> getAllUsers() throws NoShopFoundException {
        return new ResponseEntity<>(shopService.getAllShops(), HttpStatus.OK);
    }


    @GetMapping("/shopbyemail/{email}")
    public ResponseEntity<?> getShopDetailsByEmail(@PathVariable String email) throws NoShopFoundException {
        return new ResponseEntity<>(shopService.getShopDetailsByemail(email), HttpStatus.OK);
    }



}
