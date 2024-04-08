package com.example.todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class TodoController {

    @GetMapping(value = { "/", "/todo/list" })
    public String home() {
        log.info("/todo/list 주소요청");
        return "/todo/list";
    }

}
