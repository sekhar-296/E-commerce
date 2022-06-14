package com.niit.repository;

import com.niit.domain.Product;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryRepository extends Neo4jRepository<Product,String> {
    @Query(" MATCH (product:Product)WHERE product.productCategory =$productCategory MERGE (productCategory:Category {name: product.productCategory})MERGE (product)-[r:Category]->(productCategory) RETURN product")
  public List<Product> findByProductCategory(String productCategory);


}
