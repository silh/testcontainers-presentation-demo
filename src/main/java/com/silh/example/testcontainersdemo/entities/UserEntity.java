package com.silh.example.testcontainersdemo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity(name = "users")
@Table(name = "users")
public class UserEntity {

  @Id
  private Long id;
  @OneToMany
  private List<OrderEntity> orders;
}
