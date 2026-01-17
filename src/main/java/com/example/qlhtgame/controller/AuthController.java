package com.example.qlhtgame.controller;

import com.example.qlhtgame.entity.User;
import com.example.qlhtgame.security.JwtUtil;
import com.example.qlhtgame.service.PasswordService;
import com.example.qlhtgame.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepo;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final PasswordService passwordService;

    public AuthController(UserRepository userRepo,
                          JwtUtil jwtUtil,
                          PasswordEncoder passwordEncoder,
                          PasswordService passwordService) {
        this.userRepo = userRepo;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.passwordService = passwordService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("USER");
        return userRepo.save(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User dbUser = userRepo.findByUsername(user.getUsername()).orElseThrow();

        if (passwordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            return jwtUtil.generateToken(dbUser.getUsername(), dbUser.getRole());
        }
        throw new RuntimeException("Login failed");
    }


    @PostMapping("/change-password")
    public void changePassword(Authentication auth,
                               @RequestParam String oldPassword,
                               @RequestParam String newPassword,
                               @RequestParam String confirmPassword) {

        passwordService.changePassword(
                auth.getName(),
                oldPassword,
                newPassword,
                confirmPassword
        );
    }


    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String username) {
        return passwordService.forgotPassword(username);
    }


    @PostMapping("/reset-password")
    public void resetPassword(@RequestParam String token,
                              @RequestParam String newPassword,
                              @RequestParam String confirmPassword) {

        passwordService.resetPassword(token, newPassword, confirmPassword);
    }
}
