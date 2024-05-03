package com.example.movie.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.movie.dto.MemberDto;
import com.example.movie.dto.PageRequestDto;
import com.example.movie.service.MovieUserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/member")
@Controller
@Log4j2
@RequiredArgsConstructor
public class MemberController {

    private final MovieUserService service;

    // 리다이렉션 횟수가 너무 많습니다 ==> @ModelAttribute("requestDto") PageRequestDto
    // pageRequestDto 추가하기
    @GetMapping("/login")
    public void getLogin(@ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
        log.info("로그인 폼 요청");
    }

    // 로그인 시 가능함
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public void getProfile(@ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
        log.info("프로필 폼 요청");
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/edit")
    public String getProfileEdit(@ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
        log.info("프로필 수정 폼 요청");
        return "/member/edit-profile";
    }

    // /edit/nickname
    @PostMapping("/edit/nickname")
    public String editNicknamePost(MemberDto memberDto, @ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
        service.nickNameUpdate(memberDto);

        return "redirect:/member/profile";
    }

    // /edit/password
    @PostMapping("/edit/password")
    public String editPasswordPost(@RequestBody String entity) {

        return entity;
    }

}
