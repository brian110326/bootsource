package com.example.club.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class ClubRepositoryTest {

    @Autowired
    private PasswordEncoder passwordEncoder;
}
