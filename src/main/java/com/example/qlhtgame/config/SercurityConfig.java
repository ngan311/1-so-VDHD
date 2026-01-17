package com.example.qlhtgame.config;

import com.example.qlhtgame.security.JwtFilter;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
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


                        .requestMatchers(
                                "/",
                                "/login",
                                "/register",
                                "/forgot-password",
                                "/reset-password",

                                // pages
                                "/dashboard",
                                "/players",
                                "/players/**",
                                "/games",
                                "/games/**",
                                "/rooms",
                                "/rooms/**",
                                "/matches",
                                "/matches/**",
                                "/rankings",
                                "/admin/**",

                                "/css/**",
                                "/js/**",
                                "/images/**"
                        ).permitAll()



                        .requestMatchers(
                                "/api/auth/register",
                                "/api/auth/login",
                                "/api/auth/forgot-password",
                                "/api/auth/reset-password"
                        ).permitAll()
                        .requestMatchers("/api/auth/change-password").authenticated()


                        .requestMatchers("/api/admin/**").hasRole("ADMIN")


                        .requestMatchers(HttpMethod.POST, "/api/games/**", "/api/rooms/**", "/api/matches/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/games/**", "/api/rooms/**", "/api/matches/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/games/**", "/api/rooms/**").hasRole("ADMIN")


                        .requestMatchers(HttpMethod.POST, "/api/players/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/players/*/lock", "/api/players/*/unlock").hasRole("ADMIN")

                        
                        .requestMatchers(HttpMethod.GET,
                                "/api/games/**",
                                "/api/rooms/**",
                                "/api/matches/**",
                                "/api/players/**"
                        ).hasAnyRole("ADMIN", "USER")

                        .anyRequest().authenticated()
                )

                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
