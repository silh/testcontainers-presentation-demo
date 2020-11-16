package com.silh.example.testcontainersdemo.repositories;

import com.silh.example.testcontainersdemo.entities.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ProductsRepositoryTest {

  @Autowired
  private ProductsRepository productsRepository;

  @Test
  void canFetchAllProducts() {
    final List<ProductEntity> all = productsRepository.findAll();
    assertThat(all).isNotEmpty();
  }
}
