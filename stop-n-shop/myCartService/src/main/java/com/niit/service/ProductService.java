package com.niit.service;

import com.niit.domain.Cart;
import com.niit.domain.Product;
import com.niit.exception.ProductNotFoundException;

import java.util.List;


public interface ProductService {
    Cart saveProduct(Cart product) throws Exception;
    Cart getProductById(String productId) throws ProductNotFoundException;
    List<Product> getAllProducts(String email);
    boolean deleteAProduct(String email, String productId) throws ProductNotFoundException;
    Cart updateAProduct(String email, String productId, int qty) throws ProductNotFoundException;
    List deleteAllProducts(String email) throws ProductNotFoundException;
    Cart updateQuantity(Cart product,String productId) throws Exception;
    Cart getCartByEmail(String emailId);
}
