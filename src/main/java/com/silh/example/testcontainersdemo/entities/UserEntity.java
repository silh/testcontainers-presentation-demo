package com.silh.example.testcontainersdemo.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "users")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
public class UserEntity {

  @Id
  @EqualsAndHashCode.Include
  @ToString.Include
  private Long id;
  @EqualsAndHashCode.Include
  @ToString.Include
  private String name;
  @OneToMany(mappedBy = "user")
  private List<OrderEntity> orders;
}
