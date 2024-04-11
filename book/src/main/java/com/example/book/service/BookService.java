package com.example.book.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.book.dto.BookDto;
import com.example.book.entity.Book;
import com.example.book.entity.Category;
import com.example.book.entity.Publisher;
import com.example.book.repository.BookRepository;

public interface BookService {

    List<BookDto> getList();

    List<String> categoryNameList();

    Long bookCreate(BookDto dto);

    BookDto getRow(Long id);

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
