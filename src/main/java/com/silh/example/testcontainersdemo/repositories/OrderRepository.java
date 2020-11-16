package com.silh.example.testcontainersdemo.repositories;

import com.silh.example.testcontainersdemo.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
  @Query(
    nativeQuery = true,
    value =
      "select * " +
        "from orders as o, products as p, users u " +
        "join order_to_products op on op.order_id = o.id and op.product_id = p.id " +
        "where u.id = :userId and p.id = :productId"
  )
  List<OrderEntity> findByUserIdAndProductsContaining(@Param("userId") Long userId,
                                                      @Param("productId") Long productId);
}
