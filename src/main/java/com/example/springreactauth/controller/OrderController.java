package com.example.springreactauth.controller;

import com.example.springreactauth.model.Order;
import com.example.springreactauth.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Create minimal order
    @PostMapping("/confirm")
    public ResponseEntity<Order> confirmOrder(@RequestBody Order order) {
        try {
            // Ensure status defaults to CONFIRMED if not provided
            if (order.getStatus() == null) {
                order.setStatus(Order.Status.CONFIRMED);
            }

            // Set createdAt if not already set
            if (order.getCreatedAt() == null) {
                order.setCreatedAt(LocalDateTime.now());
            }

            Order savedOrder = orderRepository.save(order);
            return ResponseEntity.ok(savedOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Get all orders for testing
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        try {
            List<Order> orders = orderRepository.findAll();
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Test endpoint to check if API is working
    @GetMapping("/test")
    public String test() {
        return "Orders API is working!";
    }
}
