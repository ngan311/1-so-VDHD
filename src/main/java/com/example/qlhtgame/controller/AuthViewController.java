//package com.example.qlhtgame.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class AuthViewController {
//
//    /**
//     * Đổi mật khẩu
//     * - Chỉ dành cho user đã đăng nhập
//     * - Không trùng /login, /register, /forgot-password
//     * - Dùng layout-auth
//     */
//    @GetMapping("/change-password")
//    public String changePassword(Model model) {
//        model.addAttribute("title", "Đổi mật khẩu");
//        model.addAttribute("content", "change-password");
//        return "fragments/layout-auth";
//
//}
