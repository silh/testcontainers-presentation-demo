package com.silh.example.testcontainersdemo.repositories;

import com.silh.example.testcontainersdemo.entities.OrderEntity;
import com.zaxxer.hikari.HikariDataSource;
import eu.rekawek.toxiproxy.model.ToxicDirection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.Network;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.ToxiproxyContainer;
import org.testcontainers.utility.DockerImageName;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testcontainers.containers.PostgreSQLContainer.POSTGRESQL_PORT;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ContextConfiguration(classes = OrderRepositoryTest.TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql("classpath:/data.sql")
class OrderRepositoryTest {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private ToxiproxyContainer.ContainerProxy postgresProxy;

  @Test
  void canFetchAllOrdersForUser() throws IOException {
    postgresProxy.toxics()
                 .latency("slowPostgres", ToxicDirection.DOWNSTREAM, 10_000);
    final List<OrderEntity> all = orderRepository.findAll();

    assertThat(all).isNotEmpty();
  }

  @Test
  void canFetchByUserIdAndProductId() {
    final List<OrderEntity> found = orderRepository.findByUserIdAndProductsContaining(1L, 1L);

    assertThat(found).isNotEmpty();
    found.forEach(System.out::println);
  }

  @TestConfiguration
  static class TestConfig {

    // Create a common docker network so that containers can communicate
    private final Network network = Network.newNetwork();
    private final ToxiproxyContainer toxiProxy =
      new ToxiproxyContainer(DockerImageName.parse("shopify/toxiproxy:2.1.0"))
        .withNetwork(network);
    private final PostgreSQLContainer postgres =
      (PostgreSQLContainer) new PostgreSQLContainer(DockerImageName.parse("postgres:13.1"))
        .withNetwork(network);

    public TestConfig() throws ExecutionException, InterruptedException {
      postgres.withDatabaseName("test");
      CompletableFuture.allOf(
        CompletableFuture.runAsync(postgres::start),
        CompletableFuture.runAsync(toxiProxy::start)
      ).get();
      postgres.start();
      toxiProxy.start();
      toxiProxy.getProxy(postgres, postgres.getMappedPort(5432));
    }

    @PreDestroy
    public void destroy() {
      network.close();
    }

    @Bean
    public ToxiproxyContainer.ContainerProxy postgresProxy() {
      return toxiProxy.getProxy(postgres, POSTGRESQL_PORT);
    }

    @Bean
    public HikariDataSource dataSource(ToxiproxyContainer.ContainerProxy postgresProxy) {
      final String url = String.format(
        "jdbc:postgresql://%s:%s/test",
        postgresProxy.getContainerIpAddress(),
        postgresProxy.getProxyPort()
      );
      final HikariDataSource hikariDataSource = new HikariDataSource();
      hikariDataSource.setDriverClassName(postgres.getDriverClassName());
      hikariDataSource.setUsername(postgres.getUsername());
      hikariDataSource.setPassword(postgres.getPassword());
      hikariDataSource.setJdbcUrl(url);
      hikariDataSource.setConnectionTimeout(5_000);
      hikariDataSource.setValidationTimeout(5_000);
      return hikariDataSource;
    }
  }
}
