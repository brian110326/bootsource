package com.example.jpa.controller;

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

import com.example.jpa.dto.MemoDto;
import com.example.jpa.entity.MemoServiceImpl;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Log4j2
@RequestMapping("/memo")
@RequiredArgsConstructor
public class MemoController {

    private final MemoServiceImpl service;

    // /memo/list + GET
    @GetMapping("/list")
    public void list(Model model) {
        log.info("/memo/list 주소 요청");

        List<MemoDto> list = service.getMemoList();

        // list를 list.html 에 보여주기
        model.addAttribute("list", list);
    }

    // /memo/read?mno=3 + GET
    // /memo/modify?mno=3 + GET : 수정을 위해 화면 보여주기 요청
    @GetMapping({ "/read", "/modify" })
    public void read(Long mno, Model model) {
        log.info("read form 요청");

        MemoDto mDto = service.getMemo(mno);

        model.addAttribute("mDto", mDto);

        // template 찾기

    }

    // /memo/modify + POST : 실제 수정 요청
    @PostMapping("/modify")
    public String modify(MemoDto mDto, RedirectAttributes rttr) {

        log.info("modify 요청");
        MemoDto updateDto = service.updateMemo(mDto);

        // 수정 완료 시 /memo/read로 이동
        // modify에서 read로 갈때 다시 주소줄에 mno를 같이 보내야함
        rttr.addAttribute("mno", updateDto.getMno());
        return "redirect:/memo/read";
    }

    // /memo/delete?mno=3 + GET : 삭제요청
    @GetMapping("/delete")
    public String delete(Long mno) {
        log.info("메모 삭제 요청");
        service.deleteMemo(mno);
        // 삭제 성공 시 list로
        // redirect안써주면 기본값이 forward방식
        return "redirect:/memo/list";
    }

    // /memo/create + GET : 입력을 위해 화면 보여주기 요청
    @GetMapping("/create")
    public void getCreate(@ModelAttribute("mDto") MemoDto mDto) {
        log.info("create 주소 요청");
    }

    // /memo/create + POST : 실제 입력 요청
    @PostMapping("/create")
    public String postCreate(@Valid @ModelAttribute("mDto") MemoDto mDto, BindingResult result,
            RedirectAttributes rttr) {

        log.info("create post 요청");

        if (result.hasErrors()) {
            return "/memo/create";
        }

        service.insertMemo(mDto);

        rttr.addFlashAttribute("result", "SUCCESS");

        return "redirect:/memo/list";
    }

}
