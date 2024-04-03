package com.example.jpa.repository;

import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.entity.Board;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertTest() {
        // LongStream.range(1, 301) : 1~300
        // LongStream.rangeClosed(1, 301) : 1~301

        LongStream.rangeClosed(1, 301).forEach(i -> {
            Board board = Board.builder().id(null).title(null).content(null).writer(null).build();
        });
    }
}
