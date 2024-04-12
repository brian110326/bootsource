package com.example.book.respository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.transaction.annotation.Transactional;

import com.example.book.entity.Book;
import com.example.book.entity.Category;
import com.example.book.entity.Publisher;
import com.example.book.repository.BookRepository;
import com.example.book.repository.CategoryRepository;
import com.example.book.repository.PublisherRepository;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Test
    public void insertTest() {
        Category category1 = Category.builder().name("컴퓨터").build();
        Category category2 = Category.builder().name("경제/경영").build();
        Category category3 = Category.builder().name("인문").build();
        Category category4 = Category.builder().name("소설").build();
        Category category5 = Category.builder().name("자기계발").build();

        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);
        categoryRepository.save(category4);
        categoryRepository.save(category5);

        Publisher publisher1 = Publisher.builder().name("로드북").build();
        Publisher publisher2 = Publisher.builder().name("다산").build();
        Publisher publisher3 = Publisher.builder().name("웅진지식하우스").build();
        Publisher publisher4 = Publisher.builder().name("비룡소").build();
        Publisher publisher5 = Publisher.builder().name("을유문화사").build();

        publisherRepository.save(publisher1);
        publisherRepository.save(publisher2);
        publisherRepository.save(publisher3);
        publisherRepository.save(publisher4);
        publisherRepository.save(publisher5);

        IntStream.rangeClosed(1, 4).forEach(i -> {
            Book book = Book.builder().title("Title" + i).writer("Writer" + i).price(2000 + 5000 * i)
                    .salePrice(20000 + i)
                    .category(category1)
                    .publisher(publisher1).build();

            bookRepository.save(book);
        });

        IntStream.rangeClosed(5, 8).forEach(i -> {
            Book book = Book.builder().title("Title" + i).writer("Writer" + i).price(2000 + 5000 * i)
                    .salePrice(20000 + i)
                    .category(category2)
                    .publisher(publisher2).build();

            bookRepository.save(book);
        });

        IntStream.rangeClosed(9, 12).forEach(i -> {
            Book book = Book.builder().title("Title" + i).writer("Writer" + i).price(2000 + 5000 * i)
                    .salePrice(20000 + i)
                    .category(category3)
                    .publisher(publisher3).build();

            bookRepository.save(book);
        });

        IntStream.rangeClosed(13, 16).forEach(i -> {
            Book book = Book.builder().title("Title" + i).writer("Writer" + i).price(2000 + 5000 * i)
                    .salePrice(20000 + i)
                    .category(category4)
                    .publisher(publisher4).build();

            bookRepository.save(book);
        });

        IntStream.rangeClosed(17, 20).forEach(i -> {
            Book book = Book.builder().title("Title" + i).writer("Writer" + i).price(2000 + 5000 * i)
                    .salePrice(20000 + i)
                    .category(category5)
                    .publisher(publisher5).build();

            bookRepository.save(book);
        });
    }

    @Test
    @Transactional
    public void testBookList() {
        bookRepository.findAll().forEach(book -> {
            System.out.println(book.getPublisher());
            System.out.println(book.getCategory());
        });
    }

    @Test
    public void testCategoryNameList() {
        List<Category> list = categoryRepository.findAll();

        list.forEach(category -> {
            System.out.println(category);
        });

        // Category(id=1, name=컴퓨터) 이름만 추출
        // List<String> cateList = new ArrayList<>();

        // list.forEach(category -> {
        // cateList.add(category.getName());
        // });
        List<String> cateList = list.stream().map(entity -> entity.getName()).collect(Collectors.toList());

        cateList.forEach(System.out::println);
    }

    @Test
    public void testSearchList() {

        // Spring Data JPA 페이지 처리 객체

        // Pageable pageable = PageRequest.of(0, 10);
        // Pageable pageable = PageRequest.of(0, 10, Direction.DESC);
        // Pageable pageable = PageRequest.of(0, 10, Direction.DESC, "id");
        // 1페이지부터 10개씩, id를 기준으로 내림차순 정렬
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());

        // Page 객체 : 페이지 나누기에 필요한 메소드 제공
        // == PageDto와 같은 역할
        Page<Book> result = bookRepository.findAll(bookRepository.makePredicate(), pageable);

        System.out.println("전체 행 수 : " + result.getTotalElements());
        System.out.println("페이지 수 : " + result.getTotalPages());
        result.getContent().forEach(book -> {
            System.out.println(book);
        });
    }
}
