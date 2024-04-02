package com.example.web1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Log4j2
@Controller
public class HomeController {

    // value 경로를 요청할 때 get 방식으로
    // @RequestMapping(value = "/", method = RequestMethod.GET)
    @GetMapping("/")
    public String home() {
        // c.e.web1.controller.HomeController : home 요청
        log.info("home 요청"); // sout

        // index.html : templates 아래 경로부터 시작 확장자 빼고 파일명
        return "index";
    }

    // RedirectAttributes : redirect 시 데이터 전달할 수 있는 객체

    @GetMapping("/ex3")
    public String ex3(RedirectAttributes rttr) {
        log.info("/ex3 주소 요청");
        // response.sendRedirect("path")
        // 데이터 같이 보내고 싶을때 : path +="?bno="+bno~~~

        // rttr.addAttribute("이름", 값); parameter로 전달
        // rttr.addFlashAttribute("이름", 값); Session을 이용해서(임시로) 값을 저장

        // rttr.addAttribute("bno", 15);
        // http://localhost:8080/sample/basic?bno=15

        rttr.addFlashAttribute("bno", 15);
        return "redirect:/sample/basic"; // 경로지정(다른 controller에 있는 경로 포함해서)
    }

    // IllegalStateException: Ambiguous mapping : GetMapping 안 주소가 같을 때(전체
    // controller 검사함)
    // @GetMapping("/ex3")
    // public void ex4() {
    // log.info("/ex3 주소 요청");
    // }

}
