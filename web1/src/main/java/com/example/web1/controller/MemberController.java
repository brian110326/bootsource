package com.example.web1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.web1.dto.MemberDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/login")
    public void login() {
        log.info("/member/login 주소 요청");
    }

    // @PostMapping("/login")
    // public void loginPost(String email, String name) {
    // log.info("로그인 정보 가져오기");
    // log.info("email : {}", email);
    // log.info("name : {}", name);
    // }

    @PostMapping("/login")
    public String loginPost(@ModelAttribute("mDto") MemberDto dto, @ModelAttribute("page") int page, Model model) {
        log.info("로그인 정보 가져오기");
        log.info("email : {}", dto.getEmail());
        log.info("name : {}", dto.getName());
        log.info("page : {}", page);

        // model.addAttribute("page", page) == @ModelAttribute("page")

        return "/member/info";

        // info.html 페이지로 넘어가지만 주소줄에는 login으로 되어있음
        // forward 방식이 기본
    }

    // 데이터 전송
    // request.setAttribute("이름",값) ==> Model

}
