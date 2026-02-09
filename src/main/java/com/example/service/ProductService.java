package com.example.service;

import com.example.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    
    List<Product> getAllProducts();
    
    Optional<Product> getProductById(Long id);
    
    Product createProduct(Product product);
    
    Product updateProduct(Long id, Product product);
    
    boolean deleteProduct(Long id);
    
    List<Product> searchProductsByName(String name);
    
    List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice);
    
    List<Product> filterProducts(Integer minQuantity, String nameKeyword);
}