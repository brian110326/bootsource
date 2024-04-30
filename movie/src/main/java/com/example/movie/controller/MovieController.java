package com.example.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.movie.dto.MovieDto;
import com.example.movie.dto.PageRequestDto;
import com.example.movie.dto.PageResultDto;
import com.example.movie.service.MovieService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/movie")
public class MovieController {

    private final MovieService service;

    @GetMapping("/list")
    public void listGet(@ModelAttribute("requestDto") PageRequestDto pageRequestDto, Model model) {
        PageResultDto<MovieDto, Object[]> result = service.getList(pageRequestDto);

        model.addAttribute("result", result);
    }

    @GetMapping({ "/read", "/modify" })
    public void getRead(Long mno, Model model, @ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
        MovieDto movieDto = service.getRow(mno);
        model.addAttribute("dto", movieDto);

    }

    @PostMapping("/remove")
    public String removePost(Long mno, @ModelAttribute("requestDto") PageRequestDto pageRequestDto) {
        service.movieRemove(mno);
        return "redirect:/movie/list";
    }

    @GetMapping("/register")
    public void registerGet() {

    }

    @PostMapping("/register")
    public String registerPost(MovieDto movieDto, RedirectAttributes rttr) {
        log.info("영화 등록 {}", movieDto);

        // MovieDto(mno=null, title=, createdDate=null, lastModifiedDate=null, avg=0.0,
        // reviewCnt=null, movieImageDtos=[MovieImageDto(inum=null,
        // uuid=df45c133-0954-4802-991a-8dd7467960e6, imgName=crime1.jpg,
        // path=2024\04\29, createdDate=null, lastModifiedDate=null),
        // MovieImageDto(inum=null, uuid=99edc8d2-cabf-4217-94bc-f384eed2fbc9,
        // imgName=crime2.jpg, path=2024\04\29, createdDate=null,
        // lastModifiedDate=null), MovieImageDto(inum=null,
        // uuid=a396ce06-83fb-4b61-b01c-511f1b1f8c43, imgName=crime3.jpg,
        // path=2024\04\29, createdDate=null, lastModifiedDate=null)])

        Long mno = service.movieInsert(movieDto);

        rttr.addFlashAttribute("msg", mno);

        return "redirect:/movie/list";
    }

    @PostMapping("/modify")
    public String modifyPost(MovieDto movieDto, RedirectAttributes rttr) {
        log.info("movie 수정 요청 {}", movieDto);

        Long mno = service.movieUpdate(movieDto);

        rttr.addFlashAttribute("mno", mno);
        return "redirect:/movie/read";
    }

}
