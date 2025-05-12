package com.example.sample_spring.repository;

import com.example.sample_spring.dto.OrderSummaryDTO;
import com.example.sample_spring.model.Order;
import com.example.sample_spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
    List<Order> findByStatus(String status);
    List<Order> findByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<Order> findByUserOrderByOrderDateDesc(User user);

    @Query(value = """
            SELECT 
                o.id as orderId,
                u.username as username,
                o.order_date as orderDate,
                o.total_amount as totalAmount,
                o.status as status,
                GROUP_CONCAT(p.name SEPARATOR ', ') as productNames,
                SUM(op.quantity) as totalItems
            FROM orders o
            JOIN users u ON o.user_id = u.id
            JOIN order_products op ON o.id = op.order_id
            JOIN products p ON op.product_id = p.id
            GROUP BY o.id, u.username, o.order_date, o.total_amount, o.status
            ORDER BY o.order_date DESC
            """, nativeQuery = true)
    List<OrderSummaryProjection> findOrderSummaries();

    interface OrderSummaryProjection {
        Long getOrderId();
        String getUsername();
        LocalDateTime getOrderDate();
        Double getTotalAmount();
        String getStatus();
        String getProductNames();
        Integer getTotalItems();
    }
}