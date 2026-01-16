package com.example.qlhtgame.config;

import com.example.qlhtgame.security.JwtFilter;
import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.crypto.bcrypt.*;

@Configuration
public class SercurityConfig {

    private final JwtFilter jwtFilter;

    public SercurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // ✅ GIAO DIỆN (MVC)
                        .requestMatchers(
                                "/",
                                "/login",
                                "/players/**",
                                "/css/**",
                                "/js/**",
                                "/images/**"
                        ).permitAll()

                        // ✅ AUTH API
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/auth/forgot-password", "/api/auth/reset-password").permitAll()

                        // 🔐 API có phân quyền
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .requestMatchers("/api/players/**").hasAnyRole("ADMIN","USER")

                        // 🔒 Còn lại cần đăng nhập
                        .anyRequest().authenticated()
                )
                // ⚠️ JWT CHỈ ÁP DỤNG CHO API
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
