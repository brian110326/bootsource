package com.example.movie.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.AuthMemberDto;
import com.example.movie.dto.MemberDto;
import com.example.movie.dto.PageRequestDto;
import com.example.movie.dto.PasswordChangeDto;
import com.example.movie.service.MovieUserService;

import jakarta.servlet.http.HttpSession;

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
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/edit/nickname")
    public String editNicknamePost(MemberDto memberDto, HttpSession session) {

        service.nickNameUpdate(memberDto);

        // SecurityContent 안에 저장된 Authentication 변경되지 않음
        // 1) 현재 세션 제거 => 재로그인 : session.invalidate();

        // 2) Authentication 업데이트
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        AuthMemberDto authMemberDto = (AuthMemberDto) authentication.getPrincipal();
        authMemberDto.getMemberDto().setNickname(memberDto.getNickname());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "redirect:/member/profile";
    }

    // /edit/password
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/edit/password")
    public String editPasswordPost(PasswordChangeDto pDto, HttpSession session, RedirectAttributes rttr) {

        try {
            service.passwordUpdate(pDto);
        } catch (Exception e) {
            e.printStackTrace();
            rttr.addFlashAttribute("error", e.getMessage());
            return "redirect:/member/edit";
        }

        session.invalidate();

        return "redirect:/member/login";

    }

}
