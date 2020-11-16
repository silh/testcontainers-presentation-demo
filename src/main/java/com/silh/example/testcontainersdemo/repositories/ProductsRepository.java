package com.silh.example.testcontainersdemo.repositories;

import com.silh.example.testcontainersdemo.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<ProductEntity, Long> {
}
