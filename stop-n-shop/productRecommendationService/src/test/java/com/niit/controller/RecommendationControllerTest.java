package com.niit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.niit.domain.Product;
import com.niit.service.RecommendationService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class RecommendationControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Mock
    private RecommendationService service;
    private Product product1, product2;
    List<Product> productList;

    @InjectMocks
    private RecommendationController controller;

    @BeforeEach
    public void setUp() {
        product1 = new Product("100", "productName1", BigDecimal.valueOf(200), BigDecimal.valueOf(180), null, "productCategory1", "desription1", "brand1", 1, "city1");
        product2 = new Product("101", "productName2", BigDecimal.valueOf(500), BigDecimal.valueOf(450), null, "productCategory2", "desription2", "brand2", 2, "city2");
        productList = Arrays.asList(product1, product2);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @AfterEach
    public void tearDown() {
        product1 = null;
        product2 = null;
    }

//    @Test
//    public void givenProductSaveReturnSaveProductSuccess() throws Exception {
//        when(service.saveproduct(any())).thenReturn(product1);
//
//        mockMvc.perform(post("/api/v1/product")//making dummy http post request
//                        .contentType(MediaType.APPLICATION_JSON)//setting the content type of the post request
//                        .content(jsonToString(product1)))//firstly, java object will be converted to json string then will  be passed with the mock http request.
//                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
//        verify(service, times(1)).saveproduct(any());
//
//    }

    @Test
    public void getAllProducts() throws Exception {
        when(service.getAllProducts()).thenReturn(productList);
        mockMvc.perform(get("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(service, Mockito.times(1)).getAllProducts();
    }

    @Test
    public void getProductsByCity() throws Exception {
        when(service.getProductByCity(product1.getCity())).thenReturn(productList);
        mockMvc.perform(get("/api/v1/city/city1")
                        .contentType(anyString())
                        .content(product1.getCity()))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(service, Mockito.times(1)).getProductByCity(product1.getCity());

    }

    @Test
    public void getProductsByCategory() throws Exception {
        when(service.getProductByProductCategory(product1.getProductCategory())).thenReturn(productList);
        mockMvc.perform(get("/api/v1/category/productCategory1")
                        .contentType(anyString())
                        .content(product1.getProductCategory()))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(service, Mockito.times(1)).getProductByProductCategory(product1.getProductCategory());

    }

    @Test
    public void deleteProductById() throws Exception {
        when(service.deleteProduct(product1.getProductId())).thenReturn(true);
        mockMvc.perform(delete("/api/v1/100")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(service, times(1)).deleteProduct(anyString());

    }

    private static String jsonToString(final Object ob) throws JsonProcessingException {
        String result;

        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(ob);
            System.out.println("Json Content that has been posted:\n" + jsonContent);
            result = jsonContent;
        } catch (JsonProcessingException e) {
            result = "JSON processing error";
        }

        return result;

    }
}
