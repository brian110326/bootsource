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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

    // /memo/read?mno=3 + GET
    // /memo/modify?mno=3 + GET : 수정을 위해 화면 보여주기 요청
    @GetMapping({ "/read", "/modify" })
    public void read(Long mno, Model model) {
        log.info("read form 요청");

        MemoDto mDto = service.getMemo(mno);

        model.addAttribute("mDto", mDto);

        // template 찾기

    }

    // /memo/modify + POST : 실제 수정 요청
    @PostMapping("/modify")
    public String postMethodName(MemoDto mDto) {

        // 수정 완료 시 /memo/read로 이동
        return "/memo/read";
    }

    // /memo/delete?mno=3 + GET : 삭제요청

    // /memo/create + GET : 입력을 위해 화면 보여주기 요청

    // /memo/create + POST : 실제 입력 요청

}
