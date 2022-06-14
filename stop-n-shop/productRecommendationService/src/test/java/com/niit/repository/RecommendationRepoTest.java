//package com.niit.repository;
//
//import com.niit.domain.Product;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.neo4j.DataNeo4jTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//
//@ExtendWith(SpringExtension.class)
//@DataNeo4jTest
//public class RecommendationRepoTest {
//
//    @Autowired
//    private RecommendationRepository repository;
//    private CategoryRepository categoryRepository;
//    private Product product;
//
//    @BeforeEach
//    public void setUp() {
//        product = new Product("100", "productName1", BigDecimal.valueOf(200), BigDecimal.valueOf(180), null, "productCategory1", "desription1", "brand1", 1, "city1");
//
//    }
//
//    @AfterEach
//    public void tearDown(){
//        product=null;
//        repository.deleteAll();
////        categoryRepository.findByProductCategory("productCategory1");
//    }
//
//    @Test
//    public void givenProductToSaveShouldReturnProduct(){
//        repository.save(product);
//        Product product1 = repository.findById(product.getProductId()).get();
//        assertNotNull(product1);
//        assertEquals(product.getProductId(),product1.getProductId());
//
//    }
//
//    @Test
//    public void givenProductToDeleteShouldDeleteProduct(){
//        repository.save(product);
//        Product product1 = repository.findById(product.getProductId()).get();
//        repository.delete(product1);
//        assertEquals(Optional.empty(),repository.findById(product.getProductId()));
//    }
//
////    @Test
////    public void givenUrlToGetAllProducts(){
////
////        repository.save(product);
////        product = new Product(7, "productName2", BigDecimal.valueOf(500), BigDecimal.valueOf(450), null, "productCategory2", "desription2", "brand2", 1, "city2");
////        repository.save(product);
////
////        List<Product> list = repository.findAll();
////        assertEquals(2,list.size());
////        assertEquals("chips",list.get(1).getProductName());
////
////    }
//}
