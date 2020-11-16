package com.silh.example.testcontainersdemo.repositories;

import com.silh.example.testcontainersdemo.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
