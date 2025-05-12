package com.example.sample_spring.service;

import com.example.sample_spring.model.Order;
import com.example.sample_spring.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderService {
    Order createOrder(Order order);
    Order updateOrder(Long id, Order orderDetails);
    void deleteOrder(Long id);
    Order getOrderById(Long id);
    List<Order> getAllOrders();
    List<Order> getOrdersByUser(User user);
    List<Order> getOrdersByStatus(String status);
    List<Order> getOrdersByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    List<Order> getUserOrdersOrderedByDateDesc(User user);
    void updateOrderStatus(Long id, String status);
}