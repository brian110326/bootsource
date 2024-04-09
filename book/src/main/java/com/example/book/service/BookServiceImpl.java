package com.example.book.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.book.dto.BookDto;
import com.example.book.entity.Book;
import com.example.book.repository.BookRepository;
import com.example.book.repository.CategoryRepository;
import com.example.book.repository.PublisherRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookServiceImpl {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final PublisherRepository publisherRepository;

    public List<BookDto> getList() {
        List<Book> entities = bookRepository.findAll();
        List<BookDto> list = new ArrayList<>();

        entities.forEach(entity -> {
            list.add(entityToDto(entity));
        });

        return list;
    }

    private BookDto entityToDto(Book entity) {
        BookDto dto = BookDto.builder().id(entity.getId()).title(entity.getTitle()).writer(entity.getWriter())
                .publisher(entity.getPublisher())
                .category(entity.getCategory())
                .build();

        return dto;
    }

}
