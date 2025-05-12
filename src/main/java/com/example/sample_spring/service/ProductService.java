package com.example.sample_spring.service;

import com.example.sample_spring.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product createProduct(Product product);
    Product updateProduct(Long id, Product productDetails);
    void deleteProduct(Long id);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
    List<Product> searchProducts(String keyword);
    List<Product> getProductsInStock();
    void updateStock(Long id, Integer quantity);
    byte[] getProductMetadata(Long id);
}