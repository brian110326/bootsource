package com.example.web1.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Log4j2
@RequestMapping("/calc")
public class AddController {

    @GetMapping("/add")
    public void addGet() {
        log.info("/calc/add 주소 요청");
    }

    // 사용자 입력 값 가져오기
    // 1. HttpServletRequest req
    // 2. 파라미터 이용

    // @PostMapping("/add")
    // public void addPost(HttpServletRequest req) {
    // log.info("/calc/add post 주소 요청");
    // log.info("num1 = {}", req.getParameter("num1"));
    // log.info("num2 = {}", req.getParameter("num2"));
    // }

    @PostMapping("/add")
    public void addPost(int num1, int num2) {
        // getParameter로 하면 원래 String으로 오는데
        // 타입변환을 자동적으로 해줌
        log.info("/calc/add post 주소 요청");
        log.info("num1 = {}", num1);
        log.info("num2 = {}", num2);
    }

}
