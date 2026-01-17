package com.example.qlhtgame.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller xử lý các trang giao diện (MVC)
 */
@Controller
public class PageController {

    /**
     * Trang chủ / Dashboard
     */
    @GetMapping({"/", "/dashboard"})
    public String dashboard() {
        return "dashboard";
    }

    /**
     * Trang đăng nhập
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Trang đăng ký
     */
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    /**
     * Trang quên mật khẩu / đặt lại mật khẩu
     */
    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "forgot-password";
    }

    /**
     * Trang quản lý người chơi
     */
    @GetMapping("/players")
    public String players() {
        return "players";
    }

    /**
     * Trang quản lý game
     */
    @GetMapping("/games")
    public String games() {
        return "games";
    }

    /**
     * Trang quản lý phòng
     */
    @GetMapping("/rooms")
    public String rooms() {
        return "rooms";
    }

    /**
     * Trang quản lý trận đấu
     */
    @GetMapping("/matches")
    public String matches() {
        return "matches";
    }

    /**
     * Trang bảng xếp hạng
     */
    @GetMapping("/rankings")
    public String rankings() {
        return "rankings";
    }

    /**
     * Trang cài đặt admin
     */
    @GetMapping("/admin/settings")
    public String adminSettings() {
        return "admin-settings";
    }
}