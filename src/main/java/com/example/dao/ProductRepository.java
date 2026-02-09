package com.example.dao;

import com.example.model.Product;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ProductRepository implements IProductRepository {
    
    private static final Map<Long, Product> mockDatabase = new HashMap<>();
    private static Long idCounter = 5L;
    
    static {
        // Initialize mock data
        mockDatabase.put(1L, new Product(1L, "Laptop", "High performance laptop for professionals", 999.99, 50));
        mockDatabase.put(2L, new Product(2L, "Mouse", "Wireless mouse with ergonomic design", 29.99, 200));
        mockDatabase.put(3L, new Product(3L, "Keyboard", "Mechanical keyboard with RGB lighting", 79.99, 150));
        mockDatabase.put(4L, new Product(4L, "Monitor", "27 inch 4K monitor", 399.99, 75));
    }
    
    @Override
    public Product save(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (product.getPrice() == null || product.getPrice() <= 0) {
            throw new IllegalArgumentException("Product price must be greater than 0");
        }
        if (product.getStock() == null || product.getStock() < 0) {
            throw new IllegalArgumentException("Product stock cannot be negative");
        }
        
        if (product.getId() == null) {
            product.setId(idCounter++);
        }
        mockDatabase.put(product.getId(), product);
        return product;
    }
    
    @Override
    public Optional<Product> findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Product ID must be valid");
        }
        return Optional.ofNullable(mockDatabase.get(id));
    }
    
    @Override
    public List<Product> findAll() {
        return new ArrayList<>(mockDatabase.values());
    }
    
    @Override
    public Product update(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (product.getId() == null || product.getId() <= 0) {
            throw new IllegalArgumentException("Product ID must be valid");
        }
        if (!mockDatabase.containsKey(product.getId())) {
            throw new IllegalArgumentException("Product with ID " + product.getId() + " not found");
        }
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (product.getPrice() == null || product.getPrice() <= 0) {
            throw new IllegalArgumentException("Product price must be greater than 0");
        }
        if (product.getStock() == null || product.getStock() < 0) {
            throw new IllegalArgumentException("Product stock cannot be negative");
        }
        
        mockDatabase.put(product.getId(), product);
        return product;
    }
    
    @Override
    public void deleteById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Product ID must be valid");
        }
        if (!mockDatabase.containsKey(id)) {
            throw new IllegalArgumentException("Product with ID " + id + " not found");
        }
        mockDatabase.remove(id);
    }
    
    @Override
    public boolean existsById(Long id) {
        if (id == null || id <= 0) {
            return false;
        }
        return mockDatabase.containsKey(id);
    }
}