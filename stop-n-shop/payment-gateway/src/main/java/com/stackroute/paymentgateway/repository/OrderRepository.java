package com.stackroute.paymentgateway.repository;

import java.util.List;

import com.stackroute.paymentgateway.model.Orders;
import com.stackroute.paymentgateway.model.Product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Orders, String> {
    public abstract List<Orders> findByEmailId(String Email);

    public abstract Orders findByOrderId(String orderId);

}
