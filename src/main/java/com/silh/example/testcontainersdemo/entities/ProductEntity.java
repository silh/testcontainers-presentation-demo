package com.silh.example.testcontainersdemo.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Data
public class ProductEntity {
  @Id
  private Long id;
  private String name;
  private BigDecimal price;
}
