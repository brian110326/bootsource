package com.example.jpa.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j2
public class HomeController {
    // memo/home 보여주기
    @GetMapping("/")
    public String home() {
        log.info("/memo/home 주소 요청");
        return "/memo/home";
    }

}
