package com.example.club.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j2
@RequestMapping("/club")
public class ClubController {

    @GetMapping("/manager")
    public void managerGet() {
        log.info("manager page 요청");
    }

    @GetMapping("/admin")
    public void adminGet() {
        log.info("admin page 요청");
    }

    @GetMapping("/member")
    public void memberGet() {
        log.info("member page 요청");
    }

    @GetMapping("/member/login")
    public void getLogin() {
        log.info("로그인 페이지 요청");

    }

}
