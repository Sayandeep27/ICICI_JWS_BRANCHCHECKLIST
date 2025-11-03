package com.example.branchchecklist.repository;

import com.example.branchchecklist.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByMobile(String mobile);
}
