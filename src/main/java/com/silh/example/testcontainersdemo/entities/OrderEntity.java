package com.silh.example.testcontainersdemo.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class OrderEntity {

  @Id
  private Long id;

  @JoinTable(
    name = "order_to_products",
    joinColumns = @JoinColumn(name = "order_id"),
    inverseJoinColumns = @JoinColumn(name = "product_id")
  )
  @ManyToMany(fetch = FetchType.EAGER)
  private List<ProductEntity> products;

  @JoinColumn(name = "user_id")
  @ManyToOne(fetch = FetchType.EAGER)
  private UserEntity user;
}
