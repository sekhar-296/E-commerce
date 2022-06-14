package com.stackroute.shopsListService.repository;

import com.stackroute.shopsListService.model.Shop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShopRepository extends MongoRepository<Shop,String> {

    public abstract Optional<Shop> findByShopName(String shopName);
//    public abstract Shop findByShopId(String shopId);
//    public abstract Optional<Shop> findById(String email);
}
