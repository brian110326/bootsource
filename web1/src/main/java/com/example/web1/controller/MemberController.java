package com.example.web1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.web1.dto.LoginDto;
import com.example.web1.dto.MemberDto;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/login")
    public void login(LoginDto loginDto) {
        log.info("/member/login 주소 요청");
    }

    // @PostMapping("/login")
    // public void loginPost(String email, String name) {
    // log.info("로그인 정보 가져오기");
    // log.info("email : {}", email);
    // log.info("name : {}", name);
    // }

    // @PostMapping("/login")
    // public String loginPost(@ModelAttribute("mDto") LoginDto dto,
    // @ModelAttribute("page") int page, Model model) {
    // log.info("로그인 정보 가져오기");
    // log.info("email : {}", dto.getEmail());
    // log.info("name : {}", dto.getName());
    // log.info("page : {}", page);

    // // model.addAttribute("page", page) == @ModelAttribute("page")

    // return "/member/info";

    // // info.html 페이지로 넘어가지만 주소줄에는 login으로 되어있음
    // // forward 방식이 기본
    // }

    // @Valid LoginDto : LoginDto의 유효성 검사
    // 유효성 검사를 거치고 오류가 있으면 BindingResult 객체로 들어감
    @PostMapping("/login")
    public String loginPost(@Valid LoginDto dto, BindingResult result) {
        log.info("로그인 정보 가져오기");
        log.info("email : {}", dto.getEmail());
        log.info("name : {}", dto.getName());

        // model.addAttribute("page", page) == @ModelAttribute("page")

        // 유효성 검증을 통과하지 못한다면
        if (result.hasErrors()) {
            return "/member/login";
        }

        return "/member/info";

        // info.html 페이지로 넘어가지만 주소줄에는 login으로 되어있음
        // forward 방식이 기본
    }

    // 데이터 전송
    // request.setAttribute("이름",값) ==> Model

    @GetMapping("/join")
    public void join(MemberDto memberDto) {
        log.info("/member/join 주소 요청");
    }

    @PostMapping("/join")
    public String joinPost(@Valid MemberDto memberDto, BindingResult result) {

        if (result.hasErrors()) {
            return "/member/join";
        }

        return "redirect:/member/login";
    }

}
