package com.stackroute.productownerservice.service;

import com.google.zxing.WriterException;
import com.stackroute.productownerservice.exception.ProductNotFoundException;
import com.stackroute.productownerservice.model.Product;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    Product addProducts(Product products);
    boolean deleteProducts()throws ProductNotFoundException;
    List<Product> getProducts();
    Product findProductWithProductId(String productId) throws ProductNotFoundException;
    List<Product> findProductsByProductName(String productName)throws ProductNotFoundException;
    Product updateProductWithProductId(String productId,int quantity) throws ProductNotFoundException;
    boolean deleteProductByProductId(String productId) throws ProductNotFoundException;
    boolean checkProductId(String id);
    String generateProductId();
    List<Product> findProductBySellerEmail(String sellerEmail)throws ProductNotFoundException;
    List<Product> findProductByShopId(String shopId) throws ProductNotFoundException;
    public byte[] generateQRCodeImage(String text, int width, int height)throws WriterException, IOException;
}
