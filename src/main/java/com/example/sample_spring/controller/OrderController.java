package com.example.sample_spring.controller;

import com.example.sample_spring.dto.OrderSummaryDTO;
import com.example.sample_spring.model.Order;
import com.example.sample_spring.model.User;
import com.example.sample_spring.service.OrderService;
import com.example.sample_spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @Autowired
    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order newOrder = orderService.createOrder(order);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order orderDetails) {
        Order updatedOrder = orderService.updateOrder(id, orderDetails);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok(Map.of("deleted", true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Order order = orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Order>> getOrdersByUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        List<Order> orders = orderService.getOrdersByUser(user);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Order>> getOrdersByStatus(@PathVariable String status) {
        List<Order> orders = orderService.getOrdersByStatus(status);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<Order>> getOrdersByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<Order> orders = orderService.getOrdersByDateRange(startDate, endDate);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/user/{userId}/recent")
    public ResponseEntity<List<Order>> getUserOrdersOrderedByDateDesc(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        List<Order> orders = orderService.getUserOrdersOrderedByDateDesc(user);
        return ResponseEntity.ok(orders);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/summary")
    public ResponseEntity<List<OrderSummaryDTO>> getOrderSummaries() {
        List<OrderSummaryDTO> summaries = orderService.getOrderSummaries();
        return ResponseEntity.ok(summaries);
    }
}