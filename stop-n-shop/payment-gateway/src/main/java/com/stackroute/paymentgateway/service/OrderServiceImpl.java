package com.stackroute.paymentgateway.service;

import java.util.Collections;
import java.util.List;

import com.stackroute.paymentgateway.exception.OrderExists;
import com.stackroute.paymentgateway.exception.OrderNotFound;
import com.stackroute.paymentgateway.model.Orders;
import com.stackroute.paymentgateway.model.Product;
import com.stackroute.paymentgateway.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Orders> getOrderByEmailId(String Email) throws OrderNotFound {
        List<Orders> list=orderRepository.findByEmailId(Email);
        Collections.reverse(list);
        return list;
//        return orderRepository.findByEmailId(Email);
    }

    @Override
    public Orders getOrderById(String orderId) throws OrderNotFound {
        return orderRepository.findById(orderId).get();
    }

    @Override
    public Orders insertOrder(Orders order) throws OrderNotFound {
        return orderRepository.save(order);

    }

    @Override
    public List<Product> insertProduct(List<Product> product, String orderId) {
        Orders order = orderRepository.findByOrderId(orderId);
        if (order != null)
            order.setProducList(product);
        System.out.println(product);
        return orderRepository.save(order).getProducList();
    }

    @Override
    public Orders updateOrder(Orders order) {
        Orders orders = orderRepository.findByOrderId(order.getOrderId());
        return orderRepository.save(orders);
    }
}
