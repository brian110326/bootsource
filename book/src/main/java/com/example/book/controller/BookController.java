package com.example.book.controller;

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

import com.example.book.dto.BookDto;
import com.example.book.service.BookServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/book")
public class BookController {

    private final BookServiceImpl service;

    // @GetMapping("/list")
    // public String listGet(Model model) {
    // // List<BookDto> list = service.getList();

    // // model.addAttribute("list", list);
    // // return "/book/list";
    // }

    @GetMapping("/create")
    public void createGet(BookDto dto, Model model) {
        log.info("/book/create 주소 요청");
        // Post에서 Valid를 주면 get방식에도 null dto형태로 같이 보내야함

        // 테이블에 있는 카테고리 명 보여주기
        model.addAttribute("categories", service.categoryNameList());

    }

    @PostMapping("/create")
    public String createPost(@Valid BookDto dto, BindingResult result, RedirectAttributes rttr, Model model) {

        // @ModelAttribute : 이름 바꾸거나 model.addAttribute, 혹은 매개변수로 예를들어 int page 개별로 가져왔을
        // 때 필요 다른 페이지에서 사용X

        if (result.hasErrors()) {
            model.addAttribute("categories", service.categoryNameList());
            return "/book/create";

        }

        Long id = service.bookCreate(dto);

        rttr.addFlashAttribute("result", id);

        return "redirect:/book/list";
    }

    @GetMapping(value = { "/read", "/modify" })
    public void readGet(Long id, Model model) {
        BookDto dto = service.getRow(id);
        model.addAttribute("dto", dto);

    }

    @PostMapping("/modify")
    public String modifyPost(BookDto dto, RedirectAttributes rttr) {
        Long id = service.update(dto);
        rttr.addAttribute("id", id);

        return "redirect:/book/read";
    }

    @PostMapping("/delete")
    public String deletePost(Long id) {
        service.delete(id);
        return "redirect:/book/list";
    }

}
