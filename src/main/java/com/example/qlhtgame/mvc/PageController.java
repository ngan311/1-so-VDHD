package com.example.qlhtgame.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class PageController {

    @GetMapping({"/", "/dashboard"})
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword() {
        return "forgot-password";
    }

    @GetMapping("/players")
    public String players() {
        return "players";
    }

    @GetMapping("/games")
    public String games() {
        return "games";
    }

    @GetMapping("/rooms")
    public String rooms() {
        return "rooms";
    }

    @GetMapping("/matches")
    public String matches() {
        return "matches";
    }

    @GetMapping("/rankings")
    public String rankings() {
        return "rankings";
    }

    @GetMapping("/admin/settings")
    public String adminSettings() {
        return "admin-settings";
    }
}