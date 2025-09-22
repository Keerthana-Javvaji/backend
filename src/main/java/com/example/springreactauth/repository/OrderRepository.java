package com.example.springreactauth.repository;

import com.example.springreactauth.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
