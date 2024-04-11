package com.example.book.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.book.dto.BookDto;
import com.example.book.entity.Book;
import com.example.book.entity.Category;
import com.example.book.entity.Publisher;
import com.example.book.repository.BookRepository;
import com.example.book.repository.CategoryRepository;
import com.example.book.repository.PublisherRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final PublisherRepository publisherRepository;

    @Override
    public List<BookDto> getList() {
        List<Book> books = bookRepository.findAll(Sort.by("id").descending());

        // books.forEach(book -> {
        // list.add(entityToDto(book));
        // });

        List<BookDto> list = books.stream().map(book -> entityToDto(book)).collect(Collectors.toList());

        return list;
    }

    @Override
    public Long bookCreate(BookDto dto) {
        Category category = categoryRepository.findByName(dto.getCategoryName()).get();
        Publisher publisher = publisherRepository.findByName(dto.getPublisherName()).get();

        Book book = dtoToEntity(dto);
        book.setCategory(category);
        book.setPublisher(publisher);

        Book newBook = bookRepository.save(book);

        return newBook.getId();

    }

    @Override
    public List<String> categoryNameList() {
        List<Category> list = categoryRepository.findAll();

        List<String> cateList = list.stream().map(entity -> entity.getName()).collect(Collectors.toList());

        return cateList;
    }

    @Override
    public BookDto getRow(Long id) {
        Book book = bookRepository.findById(id).get();

        return entityToDto(book);
    }

}
