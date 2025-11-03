package com.example.branchchecklist.controller;

import com.example.branchchecklist.service.JwtService;
import com.example.branchchecklist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody Map<String, String> body) {
        String mobile = body.get("mobile");

        if (mobile == null || mobile.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Mobile number is required");
        }

        // ✅ Ensure user exists (creates if not found)
        userService.createIfNotExists(mobile);

        // ✅ Generate a signed JWT (JWS)
        String token = jwtService.generateToken(mobile);

        return ResponseEntity.ok(Map.of("token", token));
    }
}
