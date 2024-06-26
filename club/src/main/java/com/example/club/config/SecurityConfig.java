package com.example.club.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/", "/auth").permitAll()
                .requestMatchers("/club/member").hasAnyRole("USER", "MANAGER", "ADMIN")
                .requestMatchers("/club/manager").hasAnyRole("MANAGER")
                .requestMatchers("/club/admin").hasAnyRole("ADMIN"))
                .formLogin(login -> login.loginPage("/club/member/login").permitAll())
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/club/member/logout"))
                        .logoutSuccessUrl("/"));
        return http.build();
    }

    // 암호화(encode), 비밀번호 입력값 검증(matches) : PasswordEncoder
    // 단방향 암호화 : 암호화만 가능하고 복호화는 불가능

    @Bean // 객체 생성해줌
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
