package com.silh.example.testcontainersdemo.repositories;

import com.silh.example.testcontainersdemo.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
