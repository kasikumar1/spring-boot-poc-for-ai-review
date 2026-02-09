package com.example.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public String getAllProducts() {
        // Logic to retrieve all products
        return "Return all products";
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable String id) {
        // Logic to retrieve a single product by ID
        return "Return product with id: " + id;
    }

    @PostMapping
    public String createProduct(@RequestBody String product) {
        // Logic to create a new product
        return "Product created: " + product;
    }

    @PutMapping("/{id}")
    public String updateProduct(@PathVariable String id, @RequestBody String product) {
        // Logic to update the product
        return "Product updated with id: " + id;
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable String id) {
        // Logic to delete the product
        return "Product deleted with id: " + id;
    }
}