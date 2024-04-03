package com.example.jpa.repository;

import java.util.stream.LongStream;
import java.util.Optional;

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

        LongStream.rangeClosed(1, 100).forEach(i -> {
            Board board = Board.builder().title("Title" + i).content("Content" + i).writer("user" + (i % 10))
                    .build();

            boardRepository.save(board);
        });
    }

    @Test
    public void readTest() {
        Optional<Board> result = boardRepository.findById(5L);
        System.out.println(boardRepository.findById(5L));
    }

    @Test
    public void getListTest() {
        boardRepository.findAll().forEach(board -> System.out.println(board));
    }

    @Test
    public void updateTest() {
        Optional<Board> result = boardRepository.findById(500L);
        // Board board = result.get();
        result.ifPresent(board -> {
            board.setTitle("updated Title!!");
            board.setContent("updated Content!!!");
            System.out.println(boardRepository.save(board));
        });
    }

    @Test
    public void deleteTest() {
        Optional<Board> result = boardRepository.findById(499L);
        result.ifPresent(board -> {
            boardRepository.delete(board);
            System.out.println("삭제된 board : " + boardRepository.findById(499L));
        });
    }
}
