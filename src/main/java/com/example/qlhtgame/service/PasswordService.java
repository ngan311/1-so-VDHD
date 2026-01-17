package com.example.qlhtgame.service;

import com.example.qlhtgame.entity.PasswordResetToken;
import com.example.qlhtgame.entity.User;
import com.example.qlhtgame.repository.PasswordResetTokenRepository;
import com.example.qlhtgame.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PasswordService {

    private final UserRepository userRepo;
    private final PasswordResetTokenRepository tokenRepo;
    private final PasswordEncoder passwordEncoder;

    public PasswordService(UserRepository userRepo,
                           PasswordResetTokenRepository tokenRepo,
                           PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.tokenRepo = tokenRepo;
        this.passwordEncoder = passwordEncoder;
    }

    // đổi mật khẩu (đã đăng nhập)
    public void changePassword(String username,
                               String oldPassword,
                               String newPassword,
                               String confirmPassword) {

        if (!newPassword.equals(confirmPassword)) {
            throw new RuntimeException("Confirm password không khớp");
        }

        User user = userRepo.findByUsername(username).orElseThrow();

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("Mật khẩu cũ không đúng");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);
    }

    // quên mật khẩu → tạo token
    public String forgotPassword(String username) {

        User user = userRepo.findByUsername(username).orElseThrow();

        PasswordResetToken token = new PasswordResetToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setUsed(false);
        token.setExpiredAt(LocalDateTime.now().plusMinutes(30));

        tokenRepo.save(token);
        return token.getToken(); // demo: trả token ra luôn
    }

    // reset mật khẩu bằng token
    public void resetPassword(String tokenValue,
                              String newPassword,
                              String confirmPassword) {

        if (!newPassword.equals(confirmPassword)) {
            throw new RuntimeException("Confirm password không khớp");
        }

        PasswordResetToken token = tokenRepo.findByToken(tokenValue)
                .orElseThrow(() -> new RuntimeException("Token không hợp lệ"));

        if (token.isUsed()) {
            throw new RuntimeException("Token đã sử dụng");
        }

        if (token.getExpiredAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token đã hết hạn");
        }

        User user = token.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);

        token.setUsed(true);
        tokenRepo.save(token);
    }
}
