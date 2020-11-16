package com.silh.example.testcontainersdemo.repositories;

import com.silh.example.testcontainersdemo.entities.OrderEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("classpath:/data.sql")
class OrderRepositoryTest {

  @Autowired
  private OrderRepository orderRepository;

  @Test
  void canFetchAllOrdersForUser() {
    final List<OrderEntity> all = orderRepository.findAll();

    assertThat(all).isNotEmpty();
  }

  @Test
  void canFetchByUserIdAndProductId() {
    final List<OrderEntity> found = orderRepository.findByUserIdAndProductsContaining(1L, 1L);

    assertThat(found).isNotEmpty();
    found.forEach(System.out::println);
  }

//  @TestConfiguration
//  static class TestConfig {
//    private static final PostgreSQLContainer DB = new PostgreSQLContainer(DockerImageName.parse("postgres:13.1"));
//    static {
//      DB.start();
//    }
//
//
//  }
}
