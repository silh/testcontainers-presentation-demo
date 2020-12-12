package com.silh.example.testcontainersdemo.repositories;

import com.silh.example.testcontainersdemo.entities.UserEntity;
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
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  void canFetchAllUsers() {
    final List<UserEntity> users = userRepository.findAll();

    assertThat(users).isNotEmpty();
  }
}
