package com.example.book.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.book.dto.BookDto;
import com.example.book.dto.PageRequestDto;
import com.example.book.dto.PageResultDto;
import com.example.book.entity.Book;
import com.example.book.entity.Category;
import com.example.book.entity.Publisher;
import com.example.book.repository.BookRepository;

public interface BookService {

    // 페이지나누기 전
    // List<BookDto> getList();

    // 페이지 나누기 적용
    PageResultDto<BookDto, Book> getList(PageRequestDto requestDto);

    List<String> categoryNameList();

    Long bookCreate(BookDto dto);

    BookDto getRow(Long id);

    Long update(BookDto dto);

    void delete(Long id);

    public default BookDto entityToDto(Book book) {
        return BookDto.builder().id(book.getId()).title(book.getTitle()).writer(book.getWriter())
                .categoryName(book.getCategory().getName())
                .publisherName(book.getPublisher().getName()).price(book.getPrice()).salePrice(book.getSalePrice())
                .createdDate(book.getCreatedDate()).lastModifiedDate(book.getLastModifiedDate())
                .build();
    }

    public default Book dtoToEntity(BookDto dto) {

        return Book.builder().title(dto.getTitle()).writer(dto.getWriter()).price(dto.getPrice())
                .salePrice(dto.getSalePrice())
                .build();
    }

}
