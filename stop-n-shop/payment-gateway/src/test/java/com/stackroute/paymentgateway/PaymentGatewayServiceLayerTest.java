package com.stackroute.paymentgateway;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import com.stackroute.paymentgateway.exception.OrderExists;
import com.stackroute.paymentgateway.exception.OrderNotFound;
import com.stackroute.paymentgateway.model.Orders;
import com.stackroute.paymentgateway.repository.OrderRepository;
import com.stackroute.paymentgateway.service.OrderServiceImpl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PaymentGatewayServiceLayerTest {
    @Mock
    private OrderRepository orderRepository;
    private Orders orders;
    @InjectMocks
    private OrderServiceImpl orderServiceImpl;

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
    public void givenOrderTosaveReturnOrder() throws OrderNotFound {
        when(orderRepository.findById(orders.getOrderId())).thenReturn(Optional.ofNullable(null));
        when(orderRepository.save(orders)).thenReturn(orders);
        assertEquals(orders, orderServiceImpl.insertOrder(orders));
        verify(orderRepository, times(1)).findById(orders.getOrderId());
        verify(orderRepository, times(1)).save(orders);
    }

    @Test
    public void givenOrderToSaveReturnNoOrder() throws OrderNotFound {
        when(orderRepository.findById(orders.getOrderId())).thenReturn(Optional.ofNullable(null));
        when(orderRepository.save(orders)).thenReturn(null);
        assertNotEquals(orders, orderServiceImpl.insertOrder(orders));
        verify(orderRepository, times(1)).findById(orders.getOrderId());
        verify(orderRepository, times(1)).save(orders);
    }

}
