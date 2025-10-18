package com.example.branchchecklist.service;

import com.example.branchchecklist.model.User; // <-- your entity
import com.example.branchchecklist.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Create user if not found
    @Transactional
    public User createIfNotExists(String mobile) {
        User existing = userRepository.findByMobile(mobile);
        if (existing != null) return existing;

        User user = new User();
        user.setMobile(mobile);
        return userRepository.save(user);
    }

    // Load user details for authentication
    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        User user = userRepository.findByMobile(mobile);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + mobile);
        }

        UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(user.getMobile());
        builder.password(""); // no password needed (JWT-based auth)
        builder.roles("USER");
        return builder.build();
    }
}
