package com.stackroute.paymentgateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.stackroute.paymentgateway.model.Orders;
import com.stackroute.paymentgateway.repository.OrderRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class PaymentGateWayRepositoryLayerTest {

    @Autowired
    private OrderRepository orderRepository;
    private Orders orders;

    @BeforeEach
    public void setup() {
        orders = new Orders("order_JWL2no1O331Hxf", 12300, "INR", 0, 12300, "2800041289", "created", "sekhar",
                "sekhar@gmail.com", "pay_JUE5NN3UzIoCNF", null);
    }

    @AfterEach
    public void Destroy() {
        orders = null;
        orderRepository.deleteAll();
    }

    @Test
    public void givenOrderToSaveReturnOrder() {
        orderRepository.insert(orders);
        Orders result = orderRepository.findById(orders.getOrderId()).get();
        assertNotNull(result);
        assertEquals(result.getOrderId(), orders.getOrderId());
    }

    @Test
    public void getOrderDetailsWithReceiptNumber() {
        orderRepository.insert(orders);
        Orders result = orderRepository.findByOrderId(orders.getOrderId());
        assertEquals(result.getOrderId(), orders.getOrderId());
    }

    @Test
    public void getOrderByEmailId() {
        orderRepository.insert(orders);
        Orders result = orderRepository.findByEmailId(orders.getEmailId()).get(0);
        assertEquals(result.getEmailId(), orders.getEmailId());
    }

    @Test
    public void givenNameIsNotEqualToOrderDetailsName() {
        orderRepository.insert(orders);
        Orders result = orderRepository.findById(orders.getOrderId()).get();
        result.setName("vivek");
        assertNotEquals(result.getName(), orders.getName());
    }

}
