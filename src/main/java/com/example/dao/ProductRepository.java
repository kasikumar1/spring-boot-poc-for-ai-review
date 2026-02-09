package com.example.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProductRepository {
    private Map<Integer, Product> productDatabase = new HashMap<>();
    private int currentId = 1;

    public List<Product> findAll() {
        return new ArrayList<>(productDatabase.values());
    }

    public Optional<Product> findById(int id) {
        return Optional.ofNullable(productDatabase.get(id));
    }

    public Product save(Product product) {
        if (product.getId() == 0) {
            product.setId(currentId++);
        }
        productDatabase.put(product.getId(), product);
        return product;
    }

    public void deleteById(int id) {
        productDatabase.remove(id);
    }

    // Mock data for testing
    public void loadMockData() {
        save(new Product(0, "Product 1", 10.99));
        save(new Product(0, "Product 2", 15.49));
        save(new Product(0, "Product 3", 7.99));
    }
}

class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}