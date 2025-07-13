package com.example.jwt.controller;

import com.example.jwt.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Base64;

@RestController
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/authenticate")
    public ResponseEntity<?> authenticate(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Basic ")) {
            return ResponseEntity.status(401).body("Missing Authorization header");
        }

        // Decode Base64 credentials
        String base64Credentials = header.substring("Basic ".length());
        byte[] decodedBytes = Base64.getDecoder().decode(base64Credentials);
        String[] credentials = new String(decodedBytes).split(":", 2);

        if (credentials.length != 2) {
            return ResponseEntity.status(400).body("Invalid Basic Auth format");
        }

        String username = credentials[0];
        String password = credentials[1];

        // Check against hardcoded credentials
        if ("user".equals(username) && "pwd".equals(password)) {
            String token = jwtUtil.generateToken(username);
            return ResponseEntity.ok("{\"token\":\"" + token + "\"}");
        }

        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
