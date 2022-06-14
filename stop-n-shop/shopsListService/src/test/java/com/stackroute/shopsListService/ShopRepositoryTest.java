package com.stackroute.shopsListService;


import com.stackroute.shopsListService.model.Address;
import com.stackroute.shopsListService.model.Location;
import com.stackroute.shopsListService.model.Shop;
import com.stackroute.shopsListService.repository.ShopRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class ShopRepositoryTest {


    @Autowired
    private ShopRepository shopRepository;
    private Shop shop;
    private Address address;
    private Location location;

    @BeforeEach
    public void setup()
    {
        byte[] shopImage = new byte[0];
        address =new Address();
        location=new Location();
        shop = new Shop( "abc@gmail.com","Mega Mart", address,location,"4",shopImage,"kunal","8888888888","dfdf");
    }
    @AfterEach
    public void destroy()
    {
        shop=null;
        shopRepository.deleteAll();
    }

    @Test
    public void givenShopDetailsToSaveReturnsShopDetails()
    {
        shopRepository.insert(shop);
        Shop result=shopRepository.findById(shop.getEmail()).get();
        assertNotNull(result);//null if record not found

        //compare product data with result data
        assertEquals(result.getEmail(),shop.getEmail());
        assertEquals(result.getShopName(),shop.getShopName());
    }


    @Test
    public void getAllShopsReturnsAllShops()
    {
        shopRepository.insert(shop);
        List<Shop> data=shopRepository.findAll();
        assertEquals(1,data.size());
        assertEquals(data.get(0).getShopName(),shop.getShopName());
    }
}
