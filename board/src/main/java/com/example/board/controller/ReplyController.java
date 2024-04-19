package com.example.board.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.board.dto.ReplyDto;
import com.example.board.service.ReplyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Log4j2
@RequestMapping("/replies")
@RestController
public class ReplyController {

    private final ReplyService service;

    // http://localhost:8080/replies/board/100
    @GetMapping("/board/{bno}")
    public List<ReplyDto> getListByBoard(@PathVariable("bno") Long bno) {

        log.info("댓글 가져오기 {}", bno);

        List<ReplyDto> replies = service.getReplise(bno);

        return replies;
    }

    // replies/new + Post
    @PostMapping("/new")
    public ResponseEntity<Long> postReply(@RequestBody ReplyDto dto) {
        log.info("댓글 등록 {}", dto);

        Long rno = service.create(dto);
        return new ResponseEntity<Long>(rno, HttpStatus.OK);
    }

    // /{rno} + Delete
    @DeleteMapping("/{rno}")
    public ResponseEntity<String> postMethodName(@PathVariable("rno") Long rno) {
        log.info("댓글 삭제 {}", rno);
        service.remove(rno);

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

}
