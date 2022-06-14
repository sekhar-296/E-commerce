package com.niit.service;

import com.niit.domain.Product;
import com.niit.exception.CityNotFoundException;
import com.niit.exception.ProductNotFoundException;
import com.niit.repository.CategoryRepository;
import com.niit.repository.RecommendationRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
public class RecommendationServiceTest {
    @Mock
    private RecommendationRepository repository;
    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private RecommendationServiceImpl service;
    private Product product1,product2;
    List<Product>productList;

    @BeforeEach
    public void setUp() {
        product1 = new Product("100", "productName1", BigDecimal.valueOf(200), BigDecimal.valueOf(180), null, "productCategory1", "desription1", "brand1", 1, "city1");
        product2 = new Product("101", "productName2", BigDecimal.valueOf(500), BigDecimal.valueOf(450), null, "productCategory2", "desription2", "brand2", 2, "city2");
        productList = Arrays.asList(product1, product2);

    }

    @AfterEach
    public void tearDown(){
        product1=null;
        product2=null;
    }
    @Test
    public void givenProductToSaveReturnSavedProduct() throws Exception{
        when(repository.save(any())).thenReturn(product1);
        assertEquals(product1,service.saveproduct(product1));
        verify(repository,Mockito.times(1)).save(any());
//        verify(repository,times(1)).findById(any());

    }

    @Test
    public void givenProductToDeleteShouldDeleteSuccess() throws ProductNotFoundException {
        when(repository.findById(product1.getProductId())).thenReturn(Optional.ofNullable(product1));
        boolean flag = service.deleteProduct(product1.getProductId());
        assertEquals(true,flag);
        verify(repository,times(1)).deleteById(any());
        verify(repository,times(1)).findById(any());
    }

    @Test
    public void GetAllProductsReturnProductsSuccess(){
        when(repository.findAll()).thenReturn(productList);
        assertEquals(productList,service.getAllProducts());
        verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    public void givenCityToFindReturnallProducts() throws ProductNotFoundException, CityNotFoundException {
        when(repository.findByCity(any())).thenReturn(productList);
        assertEquals(productList,service.getProductByCity(product1.getCity()));
        verify(repository,Mockito.times(2)).findByCity(any());

    }

    @Test
    public void givenCategoryToFindReturnallProducts()throws Exception{

        when(categoryRepository.findByProductCategory(any())).thenReturn(productList);
        assertEquals(productList,service.getProductByProductCategory(product1.getProductCategory()));
        verify(categoryRepository,Mockito.times(2)).findByProductCategory(any());  }

}
