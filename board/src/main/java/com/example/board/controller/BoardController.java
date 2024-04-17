package com.example.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.board.dto.BoardDto;
import com.example.board.dto.PageRequestDto;
import com.example.board.dto.PageResultDto;
import com.example.board.service.BoardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService service;

    @GetMapping("/list")
    public String listGet(Model model, PageRequestDto requestDto) {
        PageResultDto<BoardDto, Object[]> result = service.getList(requestDto);
        model.addAttribute("result", result);
        return "/board/list";
    }

    @GetMapping({ "/read", "/modify" })
    public void readGet(Model model, Long bno) {
        BoardDto dto = service.getRow(bno);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/modify")
    public String modfiyPost(BoardDto dto, RedirectAttributes rttr) {
        Long bno = service.update(dto);
        rttr.addAttribute("bno", bno);
        return "redirect:/board/read";
    }

    @PostMapping("/delete")
    public String deletePost(Long bno) {
        service.removeWithReplies(bno);
        return "redirect:/board/list";
    }

}
