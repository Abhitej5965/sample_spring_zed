package com.example.sample_spring.service.impl;

import com.example.sample_spring.dto.ProductDTO;
import com.example.sample_spring.exception.ResourceNotFoundException;
import com.example.sample_spring.model.Product;
import com.example.sample_spring.repository.ProductRepository;
import com.example.sample_spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(ProductDTO productDTO) throws IOException {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());
        product.setImageUrl(productDTO.getImageUrl());
        product.setCategory(productDTO.getCategory());

        if (productDTO.getMetadata() != null && !productDTO.getMetadata().isEmpty()) {
            MultipartFile file = productDTO.getMetadata();
            product.setMetadata(file.getBytes());
            product.setMetadataName(file.getOriginalFilename());
            product.setMetadataType(file.getContentType());
            product.setMetadataSize(file.getSize());
        }

        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, ProductDTO productDTO) throws IOException {
        Product product = getProductById(id);

        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());
        product.setImageUrl(productDTO.getImageUrl());
        product.setCategory(productDTO.getCategory());

        if (productDTO.getMetadata() != null && !productDTO.getMetadata().isEmpty()) {
            MultipartFile file = productDTO.getMetadata();
            product.setMetadata(file.getBytes());
            product.setMetadataName(file.getOriginalFilename());
            product.setMetadataType(file.getContentType());
            product.setMetadataSize(file.getSize());
        }

        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "id", id));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> getProductsByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<Product> searchProducts(String keyword) {
        return productRepository.findByNameContainingIgnoreCase(keyword);
    }

    @Override
    public List<Product> getProductsInStock() {
        return productRepository.findByStockQuantityGreaterThan(0);
    }

    @Override
    public void updateStock(Long id, Integer quantity) {
        Product product = getProductById(id);
        product.setStockQuantity(quantity);
        productRepository.save(product);
    }

    @Override
    public byte[] getProductMetadata(Long id) {
        Product product = getProductById(id);
        return product.getMetadata();
    }

    @Override
    public void updateProductMetadata(Long id, MultipartFile file) throws IOException {
        Product product = getProductById(id);
        product.setMetadata(file.getBytes());
        product.setMetadataName(file.getOriginalFilename());
        product.setMetadataType(file.getContentType());
        product.setMetadataSize(file.getSize());
        productRepository.save(product);
    }

    @Override
    public void deleteProductMetadata(Long id) {
        Product product = getProductById(id);
        product.setMetadata(null);
        product.setMetadataName(null);
        product.setMetadataType(null);
        product.setMetadataSize(null);
        productRepository.save(product);
    }
}