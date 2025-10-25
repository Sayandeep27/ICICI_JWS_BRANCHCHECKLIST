package com.example.branchchecklist.service;

import com.example.branchchecklist.model.User;
import com.example.branchchecklist.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) { this.repo = repo; }

    public User createIfNotExists(String mobile) {
        return repo.findByMobile(mobile).orElseGet(() -> repo.save(new User(mobile)));
    }
}
