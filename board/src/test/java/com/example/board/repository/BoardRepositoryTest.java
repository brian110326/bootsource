package com.example.board.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.board.entity.Board;
import com.example.board.entity.Member;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertTest() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder().email("user" + i + "@gmail.com").build();
            Board board = Board.builder().title("Title" + i).content("Content" + i).writer(member).build();

            boardRepository.save(board);
        });

    }

    @Test
    public void readBoard() {
        Board board = boardRepository.findById(2L).get();
        System.out.println(board);

        // Board(bno=2, title=Title2, content=Content2,
        // writer=Member(email=user2@gmail.com, password=pwd2, name=User2))
    }
}
