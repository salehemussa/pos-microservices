package com.pos.auth_service.controller;

import com.pos.auth_service.dto.LoginRequest;
import com.pos.auth_service.dto.LoginResponse;
import com.pos.auth_service.dto.RegisterRequest;
import com.pos.auth_service.model.User;
import com.pos.auth_service.repo.UserRepository;
import com.pos.auth_service.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor  // Lombok generates constructor for final fields
public class AuthController {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest req) {
        if (userRepo.existsByUsername(req.getUsername())) return "Username already exists";

        User user = User.builder()
                .username(req.getUsername())
                .password(passwordEncoder.encode(req.getPassword()))
                .roles(Set.of("ROLE_CUSTOMER"))
                .build();

        userRepo.save(user);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest req) {
        User user = userRepo.findByUsername(req.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword()))
            throw new RuntimeException("Invalid credentials");

        String token = jwtUtil.generateToken(user.getUsername(), user.getRoles());
        return new LoginResponse(token, "Bearer");
    }
}
