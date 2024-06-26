package com.example.movie.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.movie.dto.PageRequestDto;

@Controller
@Log4j2
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "redirect:/movie/list";
    }

    // Authentication 확인용
    @PreAuthorize("permitAll()")
    @ResponseBody
    @GetMapping("/auth")
    public Authentication getAuthenticationInfo() {

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        return authentication;
    }

    @GetMapping("/access-denied")
    public void getMethodName(@ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
        log.info("접근 제한");
    }

    @GetMapping("/error")
    public String getError(@ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
        log.info("404");

        return "/except/url404";
    }

}
