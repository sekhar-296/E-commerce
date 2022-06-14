package com.niit.repository;

import com.niit.domain.Product;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface RecommendationRepository extends Neo4jRepository<Product,String> {
   @Query(" MATCH (product:Product)WHERE product.city =$city MERGE (city:City {name: product.city})MERGE (product)-[r:City]->(city) RETURN product")
//2. @Query("MATCH (product:Product)WHERE product.productCategory =$productCategory AND  product.city =$city MERGE (productCategory:Category {name: product.productCategory},city:City{n:product.city})MERGE (product)-[r:Category]->(productCategory),(product)-[r:City]->(city) RETURN product")
   List<Product> findByCity(String city);

   @Query("MATCH (a:Product) WHERE a.productCategory = $productCategory AND a.city =$city RETURN a")
   List<Product>findByCityAndProductCategory(String productCategory,String city);

}

