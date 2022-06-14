package com.niit.repository;

import com.niit.domain.Cart;
import com.niit.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends MongoRepository<Cart,String> {

//    public Cart findByProductId(String productId);
     void deleteByBuyerEmail(String buyerEmail);
//    public List<Cart> findByBuyerEmail(String email);
//    boolean deleteByBuyerEmail(String email);
     boolean deleteAllByBuyerEmail(String email);

     Optional<Cart> findByBuyerEmail(String buyerEmail);





}
