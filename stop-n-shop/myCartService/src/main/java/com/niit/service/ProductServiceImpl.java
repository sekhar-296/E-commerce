package com.niit.service;

import com.niit.domain.Cart;
import com.niit.domain.Product;
import com.niit.exception.ProductNotFoundException;
import com.niit.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class ProductServiceImpl implements ProductService {
    private ResponseEntity response;
    private CartRepository repository;

    @Autowired
    public ProductServiceImpl(CartRepository repository) {
        this.repository = repository;
    }


    @Override
    public Cart saveProduct(Cart newCart) throws Exception {
        System.out.println(newCart.getBuyerEmail());
        System.out.println(newCart.getProductList().toString());
        Optional<Cart> existingCart = repository.findByBuyerEmail(newCart.getBuyerEmail());
        if (existingCart.isPresent()) {
            updateProductQuantityInLatestCart(existingCart.get(), newCart);
            //addUniqueItemsFromExistingCart(existingCart.get(), newCart);
            repository.deleteByBuyerEmail(newCart.getBuyerEmail());
        }
        return repository.save(newCart);
    }

    private void updateProductQuantityInLatestCart(Cart existingCart, Cart newCart){
        List<Product> duplicateItem = new ArrayList<>();
        for(Product existingItem : existingCart.getProductList()){
            for (Product newCartItem : newCart.getProductList()){
                if(newCartItem.getProductId().equalsIgnoreCase(existingItem.getProductId())){
                    newCartItem.setProductQuantity(existingItem.getProductQuantity()+
                            newCartItem.getProductQuantity());
                    duplicateItem.add(existingItem);
                }
            }
        }
        existingCart.getProductList().removeAll(duplicateItem);
        newCart.getProductList().addAll(existingCart.getProductList());
        existingCart.getProductList().removeIf(product -> product != null);
    }

    /*private void addUniqueItemsFromExistingCart(Cart existingCart, Cart newCart){
        List<Product> persistExistingCartItemList = new ArrayList<>();
        for(Product existingItem : existingCart.getProductList()){
            for (Product newCartItem : newCart.getProductList()){
                if(!newCartItem.getProductId().equalsIgnoreCase(existingItem.getProductId())){
                    persistExistingCartItemList.add(existingItem);
                }
            }
        }
        newCart.getProductList().addAll(persistExistingCartItemList);
    }*/

    @Override
    public Cart getProductById(String productId) throws ProductNotFoundException {

        return repository.findByBuyerEmail(productId).get();
    }

    @Override
    public List<Product> getAllProducts(String buyerEmail) {
        return repository.findByBuyerEmail(buyerEmail).get().getProductList();

    }

    @Override
    public Cart updateAProduct(String email, String productId, int qty) throws ProductNotFoundException {
        Cart existingCart = null;
        try {
            Optional<Cart> cart = repository.findByBuyerEmail(email);
            if(!cart.isPresent()){
                throw new ProductNotFoundException();
            }
            existingCart = cart.get();
            for(Product product : existingCart.getProductList()){
                if(product.getProductId().equalsIgnoreCase(productId)){
                    product.setProductQuantity(qty);
                }
            }
            repository.deleteByBuyerEmail(email);
            repository.save(existingCart);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return existingCart;
    }

    /**
     * Delete a Single cart Item using email and product
     * @param email
     * @param productId
     * @return
     * @throws ProductNotFoundException
     */
    @Override
    public boolean deleteAProduct(String email, String productId) throws ProductNotFoundException {
        try {
            Optional<Cart> cart = repository.findByBuyerEmail(email);
            if(!cart.isPresent()){
                throw new ProductNotFoundException();
            }
            Cart existingCart = cart.get();
            existingCart.getProductList().removeIf(product -> product.getProductId().equalsIgnoreCase(productId));
            repository.deleteByBuyerEmail(email);
            repository.save(existingCart);
        }catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public List deleteAllProducts(String email) throws ProductNotFoundException {
//        if(repository.findById(email).isPresent()){
        System.out.println("this is email" + email);
        repository.deleteByBuyerEmail(email);
        return new ArrayList<>();
    }

//        throw new ProductNotFoundException();


    @Override
    public Cart updateQuantity(Cart cart, String productId) throws Exception {
       /* boolean productIdPresent = false;
        Product product = repository.findByProductId(productId);
        List<Product> productList = repository.findById(cart.getBuyerEmail()).get().getProductList();
        if (repository.findById(cart.getBuyerEmail()).isPresent()) {
            for (Product item : productList) {
                if (productId == item.getProductId()) {
                    Product product1 = item;
                    productIdPresent = true;
                    product.setProductQuantity(product.getProductQuantity() + 1);
//


                }
                else {
                    service.saveProduct(cart);
                }

            }

        }*/
        return null;
    }

    @Override
    public Cart getCartByEmail(String emailId) {
        return null;
    }
}

            //increase quantity
//if true increase the qty
            //if false add product

//    @Override
//    public List<Cart> getProductByEmail(String emailId) {
//
//        return repository.findByBuyerEmail(emailId);
//    }




