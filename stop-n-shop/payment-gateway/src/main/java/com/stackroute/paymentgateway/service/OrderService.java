package com.stackroute.paymentgateway.service;

import java.util.List;

import com.stackroute.paymentgateway.exception.OrderExists;
import com.stackroute.paymentgateway.exception.OrderNotFound;
import com.stackroute.paymentgateway.model.Orders;
import com.stackroute.paymentgateway.model.Product;

public interface OrderService {
    public abstract List<Orders> getOrderByEmailId(String Email) throws OrderNotFound;

    public abstract Orders getOrderById(String orderId) throws OrderNotFound;

    public abstract Orders insertOrder(Orders order) throws OrderExists, OrderNotFound;

    public abstract List<Product> insertProduct(List<Product> product, String orderId);

    public abstract Orders updateOrder(Orders order);

}
