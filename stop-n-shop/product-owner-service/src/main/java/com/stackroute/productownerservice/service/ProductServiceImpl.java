package com.stackroute.productownerservice.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.stackroute.productownerservice.config.Producer;
import com.stackroute.productownerservice.exception.ProductNotFoundException;
import com.stackroute.productownerservice.model.Product;
import com.stackroute.productownerservice.repository.ProductRepository;
import com.stackroute.rabbitmq.domain.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;
    private Producer producer;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository,Producer producer) {
        this.productRepository = productRepository;
        this.producer=producer;
    }

    @Override
    public Product addProducts(Product products) {
        ProductDTO productDTO=new ProductDTO();
        productDTO.setProductId(products.getProductId());
        productDTO.setProductName(products.getProductName());
        productDTO.setMrp(products.getMrp());
        productDTO.setSellingPrice(products.getSellingPrice());
        productDTO.setProductImage(products.getProductImage());
        productDTO.setProductCategory(products.getProductCategory());
        productDTO.setDescription(products.getDescription());
        productDTO.setBrand(products.getBrand());
        productDTO.setProductQuantity(products.getProductQuantity());
        productDTO.setSellerEmail(products.getSellerEmail());
        productDTO.setSellerCity(products.getSellerCity());
        producer.sendMessageToRabbitMq(productDTO);
        return  productRepository.save(products);
    }

    @Override
    public boolean deleteProducts()throws ProductNotFoundException{
        productRepository.deleteAll();
        return true;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> list=new ArrayList<>();
         productRepository.findAll().forEach(a->list.add(a));
        Collections.reverse( list);
       return list;
    }

    @Override
    public Product findProductWithProductId(String productId) throws ProductNotFoundException {
        if(productRepository.findById(productId).isEmpty()){
            throw new ProductNotFoundException();
        }
        return productRepository.findById(productId).get();
    }

    @Override
    public List<Product> findProductsByProductName(String productName) throws ProductNotFoundException {
        if(productRepository.findProductsByProductName(productName).isEmpty()){
            throw new ProductNotFoundException();
        }
        return productRepository.findProductsByProductName(productName);
    }

    @Override
    public Product updateProductWithProductId(String productId, int quantity) throws ProductNotFoundException {
        Product p=new Product();
        if(productRepository.findById(productId).isEmpty()){
            throw new ProductNotFoundException();
        }
        else{
            p=productRepository.findById(productId).get();
            p.setProductQuantity(p.getProductQuantity()-quantity);
        }
        return productRepository.save(p);
    }

    @Override
    public boolean deleteProductByProductId(String productId) throws ProductNotFoundException {
        boolean flag=false;
        if(productRepository.findById(productId).isEmpty()){
            throw new ProductNotFoundException();
        }
        else{
            productRepository.deleteById(productId);
            flag=true;
        }
        return flag;
    }

    @Override
    public boolean checkProductId(String id) {
        boolean flag=false;
        if(productRepository.findById(id).isEmpty()){
            flag=true;
        }
        else{
            flag=false;
        }
        return flag;
    }

    @Override
    public String generateProductId() {
        String prefix="SnS";
        Random r=new Random();
        int id=r.nextInt();
        String suffix=Integer.toString(id);
        String pid=prefix.concat(suffix);
        boolean idStatus=checkProductId(pid);
        if(idStatus==false){
            pid=generateProductId();
            idStatus=checkProductId(pid);
        }
        return pid;
    }

    @Override
    public List<Product> findProductBySellerEmail(String sellerEmail) throws ProductNotFoundException {
        if(productRepository.findProductBySellerEmail(sellerEmail).isEmpty()){
            throw new ProductNotFoundException();
        }
        return productRepository.findProductBySellerEmail(sellerEmail);
    }

    @Override
    public List<Product> findProductByShopId(String shopId) throws ProductNotFoundException {
        if(productRepository.findProductsByShopId(shopId).isEmpty()){
            throw new ProductNotFoundException();
        }
        return productRepository.findProductsByShopId(shopId);
    }

    @Override
    public byte[] generateQRCodeImage(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
        byte[] pngData = pngOutputStream.toByteArray();

        return pngData;
    }
}