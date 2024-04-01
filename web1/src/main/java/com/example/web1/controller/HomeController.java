package com.example.web1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Log4j2
@Controller
public class HomeController {

    // value 경로를 요청할 때 get 방식으로
    // @RequestMapping(value = "/", method = RequestMethod.GET)
    @GetMapping("/")
    public String home() {
        // c.e.web1.controller.HomeController : home 요청
        log.info("home 요청"); // sout

        // index.html : templates 아래 경로부터 시작 확장자 빼고 파일명
        return "index";
    }
}
