package com.niit.service;

import com.niit.domain.Product;
import com.niit.exception.CategoryNotFoundException;
import com.niit.exception.CityNotFoundException;
import com.niit.exception.ProductNotFoundException;

import java.util.List;
import java.util.Optional;


public interface RecommendationService {


    List<Product>getAllProducts();
    Product saveproduct(Product product) throws Exception;
    public boolean deleteProduct(String productId) throws ProductNotFoundException;
    List<Product> getProductByCity(String  city ) throws ProductNotFoundException, CityNotFoundException;
    List<Product> getProductByProductCategory(String  productCategory ) throws CategoryNotFoundException;
    Product getProductById(String productId) throws ProductNotFoundException;

    List<Product> getByCityCategory(String productCategory, String city);
    Product updateProductWithProductId(String productId,int quantity) throws ProductNotFoundException;

//    List<Product> getProductByBuyerPin(long  buyerPin ) throws Exception;


}

