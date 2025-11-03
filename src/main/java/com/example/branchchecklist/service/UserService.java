package com.example.branchchecklist.service;

import com.example.branchchecklist.model.UserEntity;
import com.example.branchchecklist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Loads the user for Spring Security authentication.
     * This is used by JwtAuthFilter.
     */
    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        Optional<UserEntity> userOpt = userRepository.findByMobile(mobile);
        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("User not found with mobile: " + mobile);
        }

        return User.builder()
                .username(userOpt.get().getMobile())
                .password("") // no password required; JWT handles authentication
                .authorities(Collections.emptyList())
                .build();
    }

    /**
     * Creates a user if not already present (called during token generation).
     */
    public void createIfNotExists(String mobile) {
        if (userRepository.findByMobile(mobile).isEmpty()) {
            UserEntity user = new UserEntity();
            user.setMobile(mobile);
            userRepository.save(user);
            System.out.println("✅ New user created: " + mobile);
        } else {
            System.out.println("ℹ️ User already exists: " + mobile);
        }
    }
}
