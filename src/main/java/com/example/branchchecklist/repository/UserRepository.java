package com.example.branchchecklist.repository;

import com.example.branchchecklist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByMobile(String mobile);
}
