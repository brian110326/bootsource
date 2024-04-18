package com.example.book.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.book.dto.BookDto;
import com.example.book.dto.PageRequestDto;
import com.example.book.dto.PageResultDto;
import com.example.book.entity.Book;
import com.example.book.service.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookRestController {

    private final BookService service;

    // /pages/1 : 1페이지의 10개 데이터 가져오기

    @GetMapping("/pages/{page}")
    public ResponseEntity<PageResultDto<BookDto, Book>> listGet(@PathVariable("page") int page) {

        PageRequestDto requestDto = new PageRequestDto();
        requestDto.setPage(page);

        PageResultDto<BookDto, Book> result = service.getList(requestDto);

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    // /read/15 : 15번에 해당하는 데이터 읽어오기
    @GetMapping("/read/{id}")
    public ResponseEntity<BookDto> readGet(@PathVariable("id") Long id) {
        BookDto dto = service.getRow(id);

        return new ResponseEntity<>(dto, HttpStatus.OK);

    }

    // @RequestBody : json으로 넘어오는 데이터를 객체 바인딩시켜줌
    @PostMapping("/book/new")
    public ResponseEntity<String> createPost(@RequestBody @Valid BookDto dto) {

        Long id = service.bookCreate(dto);

        // Valid 검증이 성공한 경우
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    // Valid 검증에 실패한 경우
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        // key, value값은 Map으로

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    // /modify/3 + 데이터
    @PutMapping("/modify/{id}")
    public ResponseEntity<String> modifyPost(@RequestBody BookDto updateDto, @PathVariable("id") Long id) {

        service.update(updateDto);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long id) {
        service.delete(id);

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
