package com.example.web1.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j2

public class BoardController {
    @GetMapping("/board/create")
    public void create() {
        log.info("/board/create 주소 요청");
    }

}
