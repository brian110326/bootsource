package com.example.book.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.book.dto.BookDto;
import com.example.book.dto.PageRequestDto;
import com.example.book.dto.PageResultDto;
import com.example.book.entity.Book;
import com.example.book.service.BookServiceImpl;

@Controller
@Log4j2
public class HomeController {

    @GetMapping("/")
    public String home() {
        log.info("/home 주소 요청");
        return "/home";
    }

}
