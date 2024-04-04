package com.example.jpa.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.jpa.dto.MemoDto;
import com.example.jpa.entity.MemoServiceImpl;

@Controller
@Log4j2
@RequestMapping("/memo")
@RequiredArgsConstructor
public class MemoController {

    private final MemoServiceImpl service;

    // /memo/list + GET
    @GetMapping("/list")
    public void list(Model model) {
        log.info("/memo/list 주소 요청");

        List<MemoDto> list = service.getMemoList();

        // list를 list.html 에 보여주기
        model.addAttribute("list", list);
    }

}
