package com.stackroute.paymentgateway;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.times;
// import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.when;

// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.razorpay.Order;
// import com.razorpay.RazorpayClient;
// import com.stackroute.paymentgateway.config.RazorPayClientConfig;
// import com.stackroute.paymentgateway.controller.OrderController;
// import com.stackroute.paymentgateway.model.Orders;
// import com.stackroute.paymentgateway.service.OrderService;

// import org.json.JSONException;
// import org.json.JSONObject;
// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.MediaType;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
// import org.springframework.test.web.servlet.setup.MockMvcBuilders;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @ExtendWith(MockitoExtension.class)
// public class PaymentGateWayControllerTest {
//     @Mock
//     private Orders order;
//     private OrderService orderservice;

//     @InjectMocks
//     OrderController orderController;

//     @Autowired
//     private MockMvc mockMvc;

//     @BeforeEach
//     public void setup() {
//         order = new Orders("order_JWL2no1O331Hxf", 12300, "INR", 0, 12300, "2800041289", "created", "sekhar",
//                 "sekhar@gmail.com", "pay_JUE5NN3UzIoCNF", null);
//     }

//     @AfterEach
//     public void Destroy() {
//         order = null;
//     }

//     private static String jsonConvertor(final Object obj) throws JsonProcessingException {
//         ObjectMapper mapper = new ObjectMapper();
//         return mapper.writeValueAsString(obj);
//     }

//     @Test
//     public void givenOrderToSaveReturnsOrder() throws Exception {

//         when(orderservice.getOrderById(order.getOrderId())).thenReturn(any());

//         mockMvc.perform(
//                 post("/order/v1/update").contentType(MediaType.APPLICATION_JSON)
//                         .content(jsonConvertor(order)))
//                 .andExpect(status().isOk())
//                 .andDo(MockMvcResultHandlers.print());
//         // verify(orderservice, times(1)).i;
//     }

// }
