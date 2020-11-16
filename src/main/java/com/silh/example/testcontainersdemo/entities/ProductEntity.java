package com.silh.example.testcontainersdemo.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity(name = "products")
@Table(name = "products")
@Data
public class ProductEntity {
  @Id
  private Long id;
  private String name;
  private BigDecimal price;
}
