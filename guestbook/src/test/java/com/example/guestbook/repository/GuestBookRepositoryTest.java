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
}
