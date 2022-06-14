package com.stackroute.shopsListService.service;

import com.stackroute.shopsListService.exception.NoShopFoundException;
import com.stackroute.shopsListService.exception.ShopAlreadyExistException;
import com.stackroute.shopsListService.model.Shop;

import java.util.List;

public interface ShopService {
    public abstract Shop saveShop(Shop shop) throws ShopAlreadyExistException;
    public abstract List<Shop> getAllShops() throws NoShopFoundException;

    public abstract Shop getShopDetailsByemail(String email) throws NoShopFoundException;

}
