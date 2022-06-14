package com.niit.rabbitmq;

import com.niit.domain.Product;
import com.niit.service.RecommendationServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
    @Autowired
    public RecommendationServiceImpl recommendationService;



    @RabbitListener(queues = "product_queue")
//    @RabbitListener
    public void getProductDetailsFromRabbitMQ(ProductDTO productDTO) throws Exception {
        Product product = new Product();
        product.setProductId(productDTO.getProductId());
        product.setProductName(productDTO.getProductName());
        product.setMrp(productDTO.getMrp());
        product.setSellingPrice(productDTO.getSellingPrice());
        product.setProductImage(productDTO.getProductImage());
        product.setProductCategory(productDTO.getProductCategory());
        product.setDesription(productDTO.getDesription());
        product.setBrand(productDTO.getBrand());
        product.setProductQuantity(productDTO.getProductQuantity());
        product.setCity(productDTO.getCity());
        recommendationService.saveproduct(product);


    }

}
// private int productId;
//    private String productName;
//    private long mrp;
//    private long sellingPrice;
//    private byte[] productImage;
//    private String productCategory;
//    private String desription;
//    private String brand;
//    private int productQuantity;
//    private String city;
