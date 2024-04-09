package com.example.book.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Log4j2
@RequestMapping("/book")
public class BookController {
    @GetMapping("/list")
    public String listGet() {
        return "/book/list";
    }

    @GetMapping("/create")
    public void createGet() {
        log.info("/book/create 주소 요청");
    }

}
