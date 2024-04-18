package com.example.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.board.dto.BoardDto;
import com.example.board.dto.PageRequestDto;
import com.example.board.dto.PageResultDto;
import com.example.board.service.BoardService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService service;

    @GetMapping("/list")
    public String listGet(Model model, @ModelAttribute("requestDto") PageRequestDto requestDto) {
        PageResultDto<BoardDto, Object[]> result = service.getList(requestDto);
        model.addAttribute("result", result);
        return "/board/list";
    }

    @GetMapping({ "/read", "/modify" })
    public void readGet(Model model, Long bno, @ModelAttribute("requestDto") PageRequestDto requestDto) {
        BoardDto dto = service.getRow(bno);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/modify")
    public String modfiyPost(BoardDto dto, RedirectAttributes rttr,
            @ModelAttribute("requestDto") PageRequestDto requestDto) {
        Long bno = service.update(dto);
        rttr.addAttribute("bno", bno);
        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());
        return "redirect:/board/read";
    }

    @PostMapping("/delete")
    public String deletePost(Long bno, @ModelAttribute("requestDto") PageRequestDto requestDto,
            RedirectAttributes rttr) {
        service.removeWithReplies(bno);

        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());
        return "redirect:/board/list";
    }

    @GetMapping("/create")
    public void createGet(BoardDto dto, @ModelAttribute("requestDto") PageRequestDto requestDto) {

    }

    @PostMapping("/create")
    public String createPost(@Valid BoardDto dto, BindingResult result,
            @ModelAttribute("requestDto") PageRequestDto requestDto, RedirectAttributes rttr) {

        if (result.hasErrors()) {
            return "/board/create";
        }

        Long bno = service.create(dto);

        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());

        return "redirect:/board/list";
    }

}
