package com.example.sample_spring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(nullable = false)
    private BigDecimal price;
    
    @Column(name = "stock_quantity", nullable = false)
    private Integer stockQuantity;
    
    @Column(name = "image_url")
    private String imageUrl;
    
    @Column
    private String category;

    @Lob
    @Column(name = "metadata", columnDefinition = "LONGBLOB")
    private byte[] metadata;

    @Column(name = "metadata_name")
    private String metadataName;

    @Column(name = "metadata_type")
    private String metadataType;

    @Column(name = "metadata_size")
    private Long metadataSize;
    
    @ManyToMany(mappedBy = "products")
    private List<Order> orders;
}