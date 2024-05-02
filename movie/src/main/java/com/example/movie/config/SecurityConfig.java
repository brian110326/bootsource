package com.example.movie.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                // 로그인 하기 전 어디 영역까지 보여줄 것인지 설정
                .requestMatchers("/", "/assets/**", "/css/**", "/js/**").permitAll()
                .requestMatchers("/movie/list", "/movie/read").permitAll()
                .requestMatchers("/upload/display").permitAll()
                .requestMatchers("/reviews/**").permitAll()
                .anyRequest().authenticated());

        // remove post시 에러나서 임시방편으로 csrf해제(form의 action값을 안줘서)
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
