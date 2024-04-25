package com.example.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class SecurityTest {

    // SecurityConfig passwordEncoder() 가 실행되면서 주입됨
    @Autowired
    private PasswordEncoder passwordEncoder; // 비밀번호 암호화시켜주는 객체, 원비밀번호와 암호화 된 비밀번호의 match 여부

    @Test
    public void testEncoder() {
        String password = "1111"; // 원 비밀번호

        // 원 비밀번호 암호화
        String encodePassword = passwordEncoder.encode(password);

        System.out.println("password : " + password);
        System.out.println("encode password : " + encodePassword);
        System.out.println(passwordEncoder.matches(password, encodePassword));
    }
}
