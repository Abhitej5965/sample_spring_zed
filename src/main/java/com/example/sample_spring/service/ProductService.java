package com.example.sample_spring.service;

import com.example.sample_spring.dto.ProductDTO;
import com.example.sample_spring.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product createProduct(ProductDTO productDTO) throws IOException;
    Product updateProduct(Long id, ProductDTO productDTO) throws IOException;
    void deleteProduct(Long id);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice);
    List<Product> searchProducts(String keyword);
    List<Product> getProductsInStock();
    void updateStock(Long id, Integer quantity);
    byte[] getProductMetadata(Long id);
    void updateProductMetadata(Long id, MultipartFile file) throws IOException;
    void deleteProductMetadata(Long id);
}