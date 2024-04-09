package com.example.todo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.todo.dto.TodoDto;
import com.example.todo.service.TodoServiceImpl;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class HomeController {
    @GetMapping(value = "/")
    public String home2() {

        log.info("/todo/list 주소요청");

        return "redirect:/todo/list";
    }
}
