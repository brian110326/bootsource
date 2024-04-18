package com.example.rest.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// 컨트롤러 종류
// @Controller - 메소드가 끝나고 찾는 대상 : 템플릿
// @RestController - 데이터 자체를 return 가능

@RestController
public class RestControllerTest {

    @GetMapping("/hello")
    public String getHello() {
        return "Hello World";
    }

}
