package com.example.movie.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie.dto.ReviewDto;
import com.example.movie.service.ReviewService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService service;

    // /reviews/3/all
    @GetMapping("/{mno}/all")
    public ResponseEntity<List<ReviewDto>> getMethodName(@PathVariable("mno") Long mno) {
        log.info("mno", mno);

        List<ReviewDto> list = service.getListOfMovie(mno);

        return new ResponseEntity<List<ReviewDto>>(list, HttpStatus.OK);
    }

    // /3 + POST
    @PostMapping("/{mno}")
    public ResponseEntity<Long> postMethodName(@RequestBody ReviewDto reviewDto) {

        log.info("리뷰 등록 {}", reviewDto);

        Long reviewNo = service.addReview(reviewDto);

        return new ResponseEntity<Long>(reviewNo, HttpStatus.OK);

    }

    @PreAuthorize("authentication.name == #email")
    @DeleteMapping("/{mno}/{reviewNo}")
    public ResponseEntity<Long> removePost(@PathVariable("reviewNo") Long reviewNo, String email) {

        service.removeReview(reviewNo);

        return new ResponseEntity<Long>(reviewNo, HttpStatus.OK);
    }

    // /299/5
    @GetMapping("/{mno}/{reviewNo}")
    public ResponseEntity<ReviewDto> reviewGet(@PathVariable("reviewNo") Long reviewNo) {

        log.info("review 가져오기 {}", reviewNo);

        ReviewDto reviewDto = service.getReview(reviewNo);

        return new ResponseEntity<ReviewDto>(reviewDto, HttpStatus.OK);
    }

    @PreAuthorize("authentication.name == #reviewDto.email")
    @PutMapping("/{mno}/{reviewNo}")
    public ResponseEntity<Long> updateReviewPut(@PathVariable("reviewNo") Long reviewNo,
            @RequestBody ReviewDto reviewDto) {
        log.info("수정된 dto {}", reviewDto);
        Long reviewNum = service.updateReview(reviewDto);

        return new ResponseEntity<Long>(reviewNum, HttpStatus.OK);
    }

}
