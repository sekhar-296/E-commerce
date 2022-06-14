package com.stackroute.productownerservice.repository;

import com.stackroute.productownerservice.model.Product;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends ElasticsearchRepository<Product,String> {
    List<Product> findProductsByProductName(String productName);
    List<Product> findProductBySellerEmail(String email);
    List<Product> findProductsByShopId(String shopid);
}
