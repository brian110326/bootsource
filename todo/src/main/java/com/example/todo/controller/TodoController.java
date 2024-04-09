package com.example.todo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.todo.dto.TodoDto;
import com.example.todo.service.TodoServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoServiceImpl service;

    // 멤버변수 초기화 : 1) 생성자 2) setter
    // @RequiredArgsConstructor : spring framework가 생성자 자동 생성(의존성 주입 : DI)

    @GetMapping(value = "/list")
    public String home(Model model) {
        log.info("/todo/list 주소요청");

        List<TodoDto> list = service.getList();

        model.addAttribute("list", list);

        return "/todo/list";
    }

    @GetMapping("/create")
    public void create() {
        log.info("/todo/create 주소 요청");
    }

    @PostMapping("/create")
    // 매개변수 dto가 title, important를 다 받아온 객체
    public String postCreate(TodoDto dto, RedirectAttributes rttr) {

        TodoDto result = service.create(dto);

        rttr.addFlashAttribute("msg", result.getId());

        return "redirect:/todo/list";
    }

    @GetMapping("/read")
    public void readGet(Long id, Model model) {
        log.info("/todo/read 주소 요청");

        TodoDto todo = service.getTodo(id);

        model.addAttribute("todo", todo);
    }

    @GetMapping("/done")
    public void getCompleted(Model model) {
        log.info("/todo/done 주소 요청");

        List<TodoDto> list = service.getCompletedList();

        model.addAttribute("list", list);

    }

}
