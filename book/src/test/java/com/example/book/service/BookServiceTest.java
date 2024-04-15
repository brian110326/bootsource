package com.example.book.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.book.dto.BookDto;
import com.example.book.dto.PageRequestDto;
import com.example.book.dto.PageResultDto;
import com.example.book.entity.Book;
import com.example.book.repository.BookRepository;

@SpringBootTest
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void testList() {
        PageRequestDto requestDto = PageRequestDto.builder().page(1).size(10).build();
        PageResultDto<BookDto, Book> pageResultDto = bookService.getList(requestDto);
    }
}
