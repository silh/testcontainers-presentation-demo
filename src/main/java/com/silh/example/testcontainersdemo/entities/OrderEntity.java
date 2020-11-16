package com.silh.example.testcontainersdemo.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity(name = "orders")
@Table(name = "orders")
@Getter
@Setter
public class OrderEntity {

  @Id
  private Long id;
  @OneToMany
  private List<ProductEntity> products;
}
