package com.example.qlhtgame.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "password_reset_tokens")
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    private boolean used;
    private LocalDateTime expiredAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // ===== getter & setter =====

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public boolean isUsed() {
        return used;
    }

    public LocalDateTime getExpiredAt() {
        return expiredAt;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public void setExpiredAt(LocalDateTime expiredAt) {
        this.expiredAt = expiredAt;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
