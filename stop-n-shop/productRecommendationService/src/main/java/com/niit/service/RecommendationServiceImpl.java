package com.niit.service;

import com.niit.domain.Product;
import com.niit.exception.CategoryNotFoundException;
import com.niit.exception.CityNotFoundException;
import com.niit.exception.EmptyInputFieldException;
import com.niit.exception.ProductNotFoundException;
import com.niit.repository.CategoryRepository;
import com.niit.repository.RecommendationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecommendationServiceImpl  implements RecommendationService{

    public RecommendationRepository repository;
   public CategoryRepository categoryRepository;



    @Autowired
    public RecommendationServiceImpl(RecommendationRepository repository,CategoryRepository categoryRepository){
        this.repository = repository;
        this.categoryRepository = categoryRepository;

    }

    @Override
    public List<Product> getAllProducts() {
        return repository.findAll();
    }


    @Override
    public Product saveproduct(Product product) throws EmptyInputFieldException, CategoryNotFoundException {
        if(product.getProductName().isEmpty()){
            throw new EmptyInputFieldException();
        }

        categoryRepository.findByProductCategory(product.getProductCategory());
//            repository.findByCity(product.getCity());

        return repository.save(product);
    }

    @Override
    public boolean deleteProduct(String productId) throws ProductNotFoundException {
        boolean flag = false;
        if(repository.findById(productId).isEmpty())
        {
            throw new ProductNotFoundException();
        }
        else {
            repository.deleteById(productId);
            flag = true;
        }
        return flag;
    }

    @Override
    public Product updateProductWithProductId(String productId, int quantity) throws ProductNotFoundException {
        Product p=new Product();
        if(repository.findById(productId).isEmpty()){
            throw new ProductNotFoundException();
        }
        else{
            p=repository.findById(productId).get();
            p.setProductQuantity(p.getProductQuantity()-quantity);
        }
        return repository.save(p);
    }

    @Override
    public List<Product> getProductByCity(String  city) throws  CityNotFoundException {

        if(repository.findByCity(city).isEmpty())
        {
            throw new CityNotFoundException();

        }

        return repository.findByCity(city);
    }

    @Override
    public List<Product> getProductByProductCategory(String productCategory) throws CategoryNotFoundException {
        if(categoryRepository.findByProductCategory(productCategory).isEmpty())
        {
            throw new CategoryNotFoundException();

        }
        return categoryRepository.findByProductCategory(productCategory);

    }

    @Override
    public Product getProductById(String productId) throws ProductNotFoundException {
        if (repository.findById(productId).isEmpty()){
            throw new ProductNotFoundException();
        };
        return repository.findById(productId).get();
    }

    @Override
    public List<Product> getByCityCategory(String productCategory, String city) {
        return repository.findByCityAndProductCategory(productCategory,city);
    }


//    @Override
//    public List<Product> getProductByBuyerPin(long buyerPin) throws Exception {
//        if(pinCodeRepository.findByBuyerPin(buyerPin).isEmpty()){
//            throw new Exception();
//        }
//        return pinCodeRepository.findByBuyerPin(buyerPin);
//    }

}
