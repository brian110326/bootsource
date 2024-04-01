package com.example.web1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping("/sample")
public class SampleController {
    // String, void 형태의 메소드로 작성

    // 404 : 경로 없음(controller의 mapping 주소를 확인)
    // 500 : ex) TemplateInputException: Error resolving template [sample/basic],
    // template might not exist or might not be accessible by any of the configured
    // Template Resolvers
    // ==> templates 폴더를 확인해본다

    // retrun 타입 결정
    // void : 경로와 일치하는 곳에 template이 존재할 때
    // String : 경로와 일치하는 곳에 template이 없을 때(template 위치와 관계없이 자유롭게 지정이 가능)

    // // http://localhost:8080/board/create
    // // http://localhost:8080/user/create
    // // http://localhost:8080/user/member/create
    // create파일이 html파일, 앞에는 다 폴더경로

    // http://localhost:8080/sample/basic 요청
    @GetMapping("/basic")
    public void basic() {
        log.info("/sample/basic 주소 요청");
    }

    // http://localhost:8080/sample/ex1
    @GetMapping("/ex1")
    public void ex1() {
        log.info("/sample/ex1 주소 요청");
    }

    @GetMapping("/ex2")
    public String ex2() {
        log.info("/sample/ex2 주소 요청");
        // return "/board/create";
        return "/index";
    }

}
