package com.stackroute.shopsListService.service;

import com.stackroute.shopsListService.exception.NoShopFoundException;
import com.stackroute.shopsListService.exception.ShopAlreadyExistException;
import com.stackroute.shopsListService.model.Shop;
import com.stackroute.shopsListService.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    private ShopRepository shopRepository;
    @Autowired
    public ShopServiceImpl(ShopRepository shopRepository){
        this.shopRepository=shopRepository;
    }


    @Override
    public Shop saveShop(Shop shop) throws ShopAlreadyExistException {
        if(shopRepository.findById(shop.getEmail()).isEmpty())
        {
            return shopRepository.save(shop);
        }
        else
        {
            throw new ShopAlreadyExistException();
        }
    }

    @Override
    public List<Shop> getAllShops() throws NoShopFoundException {
        if(shopRepository.findAll().isEmpty())
        {
            throw new NoShopFoundException();
        }
        else
        {
            return shopRepository.findAll();
        }
    }


    @Override
    public Shop getShopDetailsByemail(String email) throws NoShopFoundException {
        if(shopRepository.findById(email).isEmpty()){
            throw new NoShopFoundException();
        }else{
            System.out.println("shop found");
           return shopRepository.findById(email).get();
        }
    }


}
