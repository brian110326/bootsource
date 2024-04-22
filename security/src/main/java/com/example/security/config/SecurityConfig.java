package com.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.AntPathMatcher;

@Configuration // == @Component(@Controller, @Service) : 객체(환경설정용) 생성
@EnableWebSecurity // 웹에서 security가 적용 가능하게 함
public class SecurityConfig {

    // 접근제한 개념

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // 요청 확인
                .authorizeHttpRequests(
                        authorize -> authorize.requestMatchers("/", "/security/guest", "/auth").permitAll()
                                .requestMatchers("/security/member").hasRole("USER")
                                .requestMatchers("/security/admin").hasRole("ADMIN"))
                // .formLogin(Customizer.withDefaults()); // default 로그인 페이지 보여주기
                .formLogin(login -> login.loginPage("/member/login").permitAll())
                .logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))); // custom
                                                                                                             // logout
        // 인증처리(웹에서는 대부분 폼 로그인 작업)
        // http로 들어오는 요청에 대하여 모두 허용을 함
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        // 비밀번호를 암호화
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // 임시로 생성 => 데이터베이스에 인증을 요청하는 객체
    // InMemoryUserDetailsManager : 메모리에 등록하고 임시로 사용
    @Bean
    UserDetailsService users() {
        UserDetails user = User.builder().username("user1")
                .password("{bcrypt}$2a$10$cpqOG0ASIyJotiDshBmuTuY.vmsvsR/mFiHUyjgqj9y2KJL/WAzdu")
                .roles("USER")
                .build();

        UserDetails admin = User.builder().username("admin1")
                .password("{bcrypt}$2a$10$cpqOG0ASIyJotiDshBmuTuY.vmsvsR/mFiHUyjgqj9y2KJL/WAzdu")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
