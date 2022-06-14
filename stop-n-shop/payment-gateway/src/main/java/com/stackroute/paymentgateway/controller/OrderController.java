package com.stackroute.paymentgateway.controller;

import java.util.List;
import java.util.Map;

import com.razorpay.*;
import com.stackroute.paymentgateway.config.RazorPayClientConfig;
import com.stackroute.paymentgateway.exception.OrderExists;
import com.stackroute.paymentgateway.exception.OrderNotFound;
import com.stackroute.paymentgateway.model.Orders;
import com.stackroute.paymentgateway.model.Product;
import com.stackroute.paymentgateway.model.RandomIdUtils;
import com.stackroute.paymentgateway.service.OrderService;
import com.stackroute.paymentgateway.service.OrderServiceImpl;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/order/v1")
@RequestMapping("/api/v7/")
//@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {
    private OrderServiceImpl orderServiceImpl;
    private RazorPayClientConfig razorPayClientConfig;

    @Autowired
    public OrderController(OrderServiceImpl orderServiceImpl, RazorPayClientConfig razorPayClientConfig) {
        this.orderServiceImpl = orderServiceImpl;
        this.razorPayClientConfig = razorPayClientConfig;
    }

    @GetMapping("/information/{email}")
    public ResponseEntity<?> getAllOrderByEmail(@PathVariable String email) throws OrderNotFound {
        return new ResponseEntity<>(orderServiceImpl.getOrderByEmailId(email), HttpStatus.OK);
    }

    // note: save the order details to MongoDB
    @PostMapping("/details")
    public ResponseEntity<?> insertOrder(@RequestBody Orders order) throws Exception {
        return new ResponseEntity<>(orderServiceImpl.insertOrder(order), HttpStatus.OK);
    }

    // note: get the order detials with order ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable String id) throws OrderNotFound {
        return new ResponseEntity<>(orderServiceImpl.getOrderById(id), HttpStatus.OK);
    }

    // note: generating the new order using razorpay api
    @PostMapping("/create")
    public ResponseEntity<?> generateOrder(@RequestBody Map<String, Object> data)
            throws OrderExists, RazorpayException, OrderNotFound {
        RazorpayClient rc = new RazorpayClient(razorPayClientConfig.getKey(), razorPayClientConfig.getSecret());
        int amt = Integer.parseInt(data.get("amt").toString());
        String receipt = RandomIdUtils.nextId().toString();

        JSONObject orderObj = new JSONObject();
        orderObj.put("amount", amt * 100);
        orderObj.put("currency", "INR");
        orderObj.put("receipt", receipt);
        // create order
        Order order = rc.orders.create(orderObj);

        // note: saving order to mongoDB database
        Orders myOrders = new Orders();
        myOrders.setAmount(order.get("amount"));
        myOrders.setCurrency(order.get("currency"));
        myOrders.setAmountDue(order.get("amount_due"));
        myOrders.setAmountPaid(order.get("amount_paid"));
        myOrders.setOrderId(order.get("id"));
        myOrders.setReceipt(order.get("receipt"));
        myOrders.setStatus(order.get("status"));
        myOrders.setName(data.get("name").toString());
        myOrders.setEmailId(data.get("email").toString());
        orderServiceImpl.insertOrder(myOrders);
        return new ResponseEntity<>(order.toString(), HttpStatus.OK);
    }

    @PatchMapping("/productInformation")
    public ResponseEntity<?> updateProductOrder(@RequestBody List<Product> product, @RequestParam String orderId) {
        product.forEach(s -> System.out.println(s));
        return new ResponseEntity<>(orderServiceImpl.insertProduct(product, orderId), HttpStatus.OK);
    }

    // note: controller method to update order details
    @PatchMapping("/update")
    public ResponseEntity<?> updateOrder(@RequestBody Map<String, Object> data) throws OrderNotFound, OrderExists {
        Orders order = orderServiceImpl.getOrderById(data.get("order_id").toString());
        System.out.println(order);
        order.setPaymentId(data.get("payment_id").toString());
        order.setStatus(data.get("status").toString());
        orderServiceImpl.insertOrder(order);
        System.out.println(data.get("payment_id").toString());

        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
}
