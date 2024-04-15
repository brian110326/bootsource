package com.example.guestbook.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.guestbook.dto.GuestBookDto;
import com.example.guestbook.service.GuestBookService;

@Controller
@Log4j2
@RequestMapping("/guestbook")
@RequiredArgsConstructor
public class GuestBookController {

    private final GuestBookService service;

    @GetMapping("/list")
    public void listGet(Model model) {
        List<GuestBookDto> list = service.getList();
        model.addAttribute("list", list);

    }

    @GetMapping("/read")
    public void readGet(Long gno, Model model) {
        GuestBookDto dto = service.getRow(gno);
        model.addAttribute("dto", dto);
    }

}
