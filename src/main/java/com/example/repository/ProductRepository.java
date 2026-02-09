package com.example.repository;

import com.example.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    // Custom query methods
    List<Product> findByNameContainingIgnoreCase(String name);
    
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
    
    Optional<Product> findByNameIgnoreCase(String name);
    
    // Filter methods for quantity and name
    List<Product> findByQuantityGreaterThanEqual(Integer minQuantity);
    
    List<Product> findByQuantityGreaterThanEqualAndNameContainingIgnoreCase(Integer minQuantity, String nameKeyword);
}