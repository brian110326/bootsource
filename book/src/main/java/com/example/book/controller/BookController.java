package com.example.book.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.book.dto.BookDto;
import com.example.book.service.BookServiceImpl;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookServiceImpl service;

    @GetMapping("/list")
    public String listGet(Model model) {
        List<BookDto> list = service.getList();

        model.addAttribute("list", list);
        return "/book/list";
    }

    @GetMapping("/create")
    public void createGet() {
        log.info("/book/create 주소 요청");

    }

}
