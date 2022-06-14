//package com.stackroute.productownerservice.repository;
//
//import com.stackroute.productownerservice.model.Product;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.math.BigDecimal;
//import java.util.Arrays;
//import java.util.List;
//
//@ExtendWith(MockitoExtension.class)
//public class ProductRepositoryTest {
//    @Mock
//    private ProductRepository productRepository;
//    private Product product1, product2,product3;
//    List<Product> productList;
//    List<Product> plist;
//
//    @BeforeEach
//    public void setUp(){
//        System.out.println("Test case running");
//        product1 = new Product("productId1","productName1", BigDecimal.valueOf(199),BigDecimal.valueOf(155),null,"category1","abcd","brand1",100,"seller1@gmail.com","city1","Id1",null);
//        product2 = new Product("productId2","productName2", BigDecimal.valueOf(299),BigDecimal.valueOf(255),null,"category2","abc2","brand2",109,"seller2@gmail.com","city2","Id2",null);
//        product3 = new Product("productId1","productName1", BigDecimal.valueOf(599),BigDecimal.valueOf(555),null,"category1","abcd","brand1",100,"seller1@gmail.com","city1","Id3",null);
//        productList = Arrays.asList(product1,product2);
//        plist=Arrays.asList(product1);
//    }
//
//    @AfterEach
//    public void tearDown()
//    {
//        product1=null;
//        product2 = null;
//        plist=null;
//        productList=null;
//        productRepository.deleteAll();
//        System.out.println("Test case stopping");
//    }
////    @Test
////    public void givenProductToSaveShouldReturnProduct(){
////        productRepository.save(product1);
////        Product product = productRepository.findById(product1.getProductId()).get();
////        assertNotNull(product);
////        assertEquals(product1.getProductId(),product.getProductId());
////    }
//}
