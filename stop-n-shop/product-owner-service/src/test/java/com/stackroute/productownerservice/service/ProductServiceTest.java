//package com.stackroute.productownerservice.service;
//
//import com.stackroute.productownerservice.config.Producer;
//import com.stackroute.productownerservice.exception.ProductNotFoundException;
//import com.stackroute.productownerservice.model.Product;
//import com.stackroute.productownerservice.repository.ProductRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.math.BigDecimal;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class ProductServiceTest {
//    @Mock
//    private ProductRepository productRepository;
//
//    @InjectMocks
//    private ProductServiceImpl productService;
//    private Product product1, product2,product3;
//    List<Product> productList;
//    List<Product> plist;
//    @Mock
//    Producer producer;
//
//    @BeforeEach
//    public void setUp(){
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
//    }
//
//    @Test
//    public void givenProductToSaveReturnSavedProductSuccess(){
//        when(productRepository.save(any())).thenReturn(product1);
//        assertEquals(product1,productService.addProducts(product1));
//        verify(productRepository,times(1)).save(any());
//    }
//
//    @Test
//    public void givenProductToDeleteShouldDeleteSuccess() throws ProductNotFoundException {
//        when(productRepository.findById(product1.getProductId())).thenReturn(Optional.ofNullable(product1));
//        boolean flag = productService.deleteProductByProductId(product1.getProductId());
//        assertEquals(true,flag);
//        verify(productRepository,times(1)).deleteById(any());
//        verify(productRepository,times(1)).findById(any());
//    }
//
//    @Test
//    public void DeleteAllProductReturnDeleteAllProductsSuccess() throws ProductNotFoundException {
//        boolean flag=productService.deleteProducts();
//        assertEquals(true,flag);
//        verify(productRepository,times(1)).deleteAll();
//    }
//    @Test
//    public void GetAllProductsReturnProductsSuccess(){
//        when(productRepository.findAll()).thenReturn(productList);
//        assertEquals(productList,productService.getProducts());
//        verify(productRepository,times(1)).findAll();
//    }
//    @Test
//    public void FindProductsByProductNameReturnSuccess()throws ProductNotFoundException{
//        when(productRepository.findProductsByProductName(any())).thenReturn(plist);
//        assertEquals(plist,productService.findProductsByProductName(product1.getProductName()));
//        verify(productRepository,times(2)).findProductsByProductName(any());
//    }
//    @Test
//    public void GivenProductIdToCheckReturnStatus(){
//        boolean flag=productService.checkProductId(product2.getProductId());
//        assertEquals(true,flag);
//        verify(productRepository,times(1)).findById(product2.getProductId());
//
//    }
//    @Test
//    public void GenerateProductIdReturnSuccess(){
//        boolean flag=false;
//        String productId=productService.generateProductId();
//        if(productId!=null || productId!=""){
//            flag=true;
//        }
//        assertEquals(true,flag);
//
//    }
//
//}
