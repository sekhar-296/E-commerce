package com.stackroute.productownerservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.productownerservice.model.Product;
import com.stackroute.productownerservice.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;
    private Product product1, product2,product3;
    List<Product> productList;
    List<Product> plist;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp(){
        product1 = new Product("productId1","productName1", BigDecimal.valueOf(199),BigDecimal.valueOf(155),null,"category1","abcd","brand1",100,"seller1@gmail.com","city1","Id1",null);
        product2 = new Product("productId2","productName2", BigDecimal.valueOf(299),BigDecimal.valueOf(255),null,"category2","abc2","brand2",109,"seller2@gmail.com","city2","Id2",null);
        product3 = new Product("productId1","productName1", BigDecimal.valueOf(599),BigDecimal.valueOf(555),null,"category1","abcd","brand1",100,"seller1@gmail.com","city1","Id3",null);
        productList = Arrays.asList(product1,product2);

        plist=Arrays.asList(product1);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @AfterEach
    public void tearDown()
    {
        product1=null;
        product2 = null;
        plist=null;
        productList=null;
    }
//    @Test
//    public void givenProducttoSaveReturnSaveSuccess() throws Exception{
//        when(productService.addProducts(any())).thenReturn(product1);
//        mockMvc.perform(post("/addProduct")
//                .contentType(MediaType.APPLICATION_JSON,MediaType.MULTIPART_FORM_DATA)
//                .content(product1,"Good" ))
//                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
//        verify(productService,times(1)).addProducts(any());
//
//    }

//    @Test
//    public void givenProducttoUpdateReturnUpdateProductSuccess() throws Exception {
//        when(productService.updateProductWithProductId(any())).thenReturn(product1);
//        mockMvc.perform(put("/updateProduct/productId1")//making dummy http post request
//                        .contentType(MediaType.APPLICATION_JSON)//setting the content type of the post request
//                        .content(jsonToString(product1)))//firstly, java object will be converted to json string then will  be passed with the mock http request.
//                .andExpect(status().isOk()).andDo(MockMvcResultHandlers.print());
//        verify(productService,times(1)).updateProductWithProductId(any());
//
//    }

    @Test
    public void givenProductIDDeleteProduct() throws Exception {
        when(productService.deleteProductByProductId(anyString())).thenReturn(true);
        mockMvc.perform(delete("/delete/productId1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(productService,times(1)).deleteProductByProductId(anyString());

    }

    @Test
    public void deleteAllProductReturnSuccess() throws Exception {
        when(productService.deleteProducts()).thenReturn(true);
        mockMvc.perform(delete("/deleteAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(productService,times(1)).deleteProducts();
    }
    @Test
    public void getAllProducts() throws Exception {
        when(productService.getProducts()).thenReturn(productList);
        mockMvc.perform(get("/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
        verify(productService,times(1)).getProducts();
    }
//    @Test
//    public void findProductByNameReturnProductSuccess() throws Exception {
//        when(productService.findProductsByProductName(product1.getProductName())).thenReturn(plist);
//        mockMvc.perform(get("/product/productName1")
//                        .contentType(anyString())
//                        .content(product1.getProductName()))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//        verify(productService,times(1)).findProductsByProductName(product1.getProductName());
//    }


    private static String jsonToString(final Object ob) throws JsonProcessingException {
        String result;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(ob);
            System.out.println("Json Content that has been posted:\n"+jsonContent);
            result = jsonContent;
        } catch(JsonProcessingException e) {
            result = "JSON processing error";
        }
        return result;
    }



}
