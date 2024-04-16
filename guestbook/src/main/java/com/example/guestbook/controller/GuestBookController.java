package com.example.guestbook.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.guestbook.dto.GuestBookDto;
import com.example.guestbook.dto.PageRequestDto;
import com.example.guestbook.dto.PageResultDto;
import com.example.guestbook.entity.GuestBook;
import com.example.guestbook.service.GuestBookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Log4j2
@RequestMapping("/guestbook")
@RequiredArgsConstructor
public class GuestBookController {

    private final GuestBookService service;

    @GetMapping("/list")
    public void listGet(Model model, PageRequestDto requestDto) {
        PageResultDto<GuestBookDto, GuestBook> result = service.getList(requestDto);
        model.addAttribute("result", result);

    }

    @GetMapping({ "/read", "/modify" })
    public void readGet(Long gno, Model model) {
        GuestBookDto dto = service.getRow(gno);
        model.addAttribute("dto", dto);
    }

    @PostMapping("/modify")
    public String modifyPost(GuestBookDto dto, RedirectAttributes rttr) {
        Long gno = service.update(dto);
        rttr.addAttribute("gno", gno);
        return "redirect:/guestbook/read";
        // String => LocalDateTime type mismatch
        // form이 post방식으로 넘어갈 때 String 형태로 감 ==> LocalDateTime의 name속성을 없애면 가능
    }

    @PostMapping("/delete")
    public String deletePost(Long gno) {
        service.delete(gno);
        return "redirect:/guestbook/list";
    }

}
