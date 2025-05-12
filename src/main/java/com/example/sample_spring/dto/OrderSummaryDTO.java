package com.example.sample_spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderSummaryDTO {
    private Long orderId;
    private String username;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
    private String status;
    private String productNames;  // Concatenated product names
    private Integer totalItems;   // Total number of items in order
}