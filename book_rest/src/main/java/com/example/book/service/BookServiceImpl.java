package com.example.book.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.book.dto.BookDto;
import com.example.book.dto.PageRequestDto;
import com.example.book.dto.PageResultDto;
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

    // @Override
    // public List<BookDto> getList() {
    // List<Book> books = bookRepository.findAll(Sort.by("id").descending());

    // // books.forEach(book -> {
    // // list.add(entityToDto(book));
    // // });

    // List<BookDto> list = books.stream().map(book ->
    // entityToDto(book)).collect(Collectors.toList());

    // return list;
    // }

    @Override
    public PageResultDto<BookDto, Book> getList(PageRequestDto requestDto) {

        // 페이지번호를 0으로 계속 고정할 순 X
        // Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());

        // 매개변수의 requestDto에 페이지 정보와 사이즈 정보가 있기 때문에 Sort만 함
        Pageable pageable = requestDto.getPageable(Sort.by("id").descending());

        Page<Book> result = bookRepository
                .findAll(bookRepository.makePredicate(requestDto.getType(), requestDto.getKeyword()), pageable);
        Function<Book, BookDto> fn = (entity -> entityToDto(entity));
        return new PageResultDto(result, fn);
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

    @Override
    public Long update(BookDto dto) {
        Book book = bookRepository.findById(dto.getId()).get();

        book.setPrice(dto.getPrice());
        book.setSalePrice(dto.getSalePrice());

        bookRepository.save(book);

        return entityToDto(book).getId();
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

}
