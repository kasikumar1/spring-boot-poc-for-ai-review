package com.example.dao;

import com.example.model.Product;
import java.util.List;
import java.util.Optional;

public interface IProductRepository {
    
    // Create a new product
    Product save(Product product);
    
    // Read a product by ID
    Optional<Product> findById(Long id);
    
    // Read all products
    List<Product> findAll();
    
    // Update a product
    Product update(Product product);
    
    // Delete a product by ID
    void deleteById(Long id);
    
    // Check if product exists
    boolean existsById(Long id);
}