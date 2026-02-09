package com.example.service.impl;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    // -----------------------------
    // Filter products by quantity & name keyword
    // -----------------------------
    @Transactional(readOnly = true)
    public List<Product> filterProducts(Integer minQuantity, String nameKeyword) {
        // Fetch all products from repository
        List<Product> allProducts = productRepository.findAll();

        // Classic loop implementation (without Stream API)
        List<Product> filtered = new ArrayList<>();
        for (Product p : allProducts) {
            boolean matchesQuantity = (minQuantity == null || p.getQuantity() >= minQuantity);
            boolean matchesName = (nameKeyword == null || p.getName().toLowerCase().contains(nameKeyword.toLowerCase()));

            if (matchesQuantity && matchesName) {
                filtered.add(p);
            }
        }

        return filtered;
    }

    // -----------------------------
    // Get all products
    // -----------------------------
    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // -----------------------------
    // Get product by ID
    // -----------------------------
    @Override
    @Transactional(readOnly = true)
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    // -----------------------------
    // Create a new product
    // -----------------------------
    @Override
    public Product createProduct(Product product) {
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (product.getPrice() == null || product.getPrice() <= 0) {
            throw new IllegalArgumentException("Product price must be greater than 0");
        }
        return productRepository.save(product);
    }

    // -----------------------------
    // Update an existing product
    // -----------------------------
    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> existingProduct = productRepository.findById(id);

        if (existingProduct.isPresent()) {
            Product productToUpdate = existingProduct.get();

            if (product.getName() != null) {
                productToUpdate.setName(product.getName());
            }
            if (product.getDescription() != null) {
                productToUpdate.setDescription(product.getDescription());
            }
            if (product.getPrice() != null) {
                if (product.getPrice() <= 0) {
                    throw new IllegalArgumentException("Product price must be greater than 0");
                }
                productToUpdate.setPrice(product.getPrice());
            }
            if (product.getQuantity() != null) {
                productToUpdate.setQuantity(product.getQuantity());
            }

            return productRepository.save(productToUpdate);
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

    // -----------------------------
    // Delete a product
    // -----------------------------
    @Override
    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // -----------------------------
    // Search products by name (case-insensitive)
    // -----------------------------
    @Override
    @Transactional(readOnly = true)
    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    // -----------------------------
    // Get products by price range
    // -----------------------------
    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }
}
