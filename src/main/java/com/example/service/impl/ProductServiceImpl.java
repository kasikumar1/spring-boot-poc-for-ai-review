package com.example.service.impl;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
@Transactional(readOnly = true)
public List<Product> filterProducts(Integer minQuantity, String nameKeyword) {
    List<Product> allProducts = productRepository.findAll();

    // Classic loop implementation (without Stream API)
    List<Product> filtered = new ArrayList<>();
    for (Product p : allProducts) {
        if ((minQuantity == null || p.getQuantity() >= minQuantity) &&
            (nameKeyword == null || p.getName().toLowerCase().contains(nameKeyword.toLowerCase()))) {
            filtered.add(p);
        }
    }
    return filtered;
    
}


    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product createProduct(Product product) {
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty");
        }
        if (product.getPrice() == null || product.getPrice() < =0) {
            throw new IllegalArgumentException("Product price must be valid");
        }
        return productRepository.save(product);
    }

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

    @Override
    public boolean deleteProduct(Long id) {
        if (productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> searchProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }
}