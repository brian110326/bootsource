package com.example.guestbook.repository;

import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.guestbook.entity.GuestBook;

@SpringBootTest
public class GuestBookRepositoryTest {

    @Autowired
    private GuestBookRepository guestBookRepository;

    @Test
    public void insertTest() {
        LongStream.rangeClosed(1, 300).forEach(i -> {
            GuestBook guestBook = GuestBook.builder().writer("Writer" + i).title("Title" + i).content("Content" + i)
                    .build();

            guestBookRepository.save(guestBook);
        });
    }

    @Test
    public void testList() {
        guestBookRepository.findAll().forEach(guestbook -> {
            System.out.println(guestbook);
        });
    }

    @Test
    public void readTest() {
        GuestBook guestBook = guestBookRepository.findById(300L).get();
        System.out.println(guestBook);
    }

    @Test
    public void modifyTest() {
        GuestBook guestBook = guestBookRepository.findById(300L).get();
        guestBook.setTitle("The First 300");
        guestBookRepository.save(guestBook);
    }

    @Test
    public void deleteTest() {
        guestBookRepository.deleteById(301L);
    }
}
