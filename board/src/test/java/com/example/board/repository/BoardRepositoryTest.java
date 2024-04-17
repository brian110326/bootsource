package com.example.board.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.board.entity.Board;
import com.example.board.entity.Member;

import jakarta.transaction.Transactional;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void insertTest() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder().email("user" + i + "@gmail.com").build();
            Board board = Board.builder().title("Title" + i).content("Content" + i).writer(member).build();

            boardRepository.save(board);
        });

    }

    @Test
    @Transactional
    public void readBoard() {
        Board board = boardRepository.findById(2L).get();
        System.out.println(board);

        // Board(bno=2, title=Title2, content=Content2,
        // writer=Member(email=user2@gmail.com, password=pwd2, name=User2))

        System.out.println(board.getWriter());
    }

    @Test
    public void testList() {
        List<Object[]> list = boardRepository.list();

        for (Object[] objects : list) {
            // System.out.println(Arrays.toString(objects));
            Board board = (Board) objects[0];
            Member member = (Member) objects[1];
            Long replyCnt = (Long) objects[2];

            System.out.println(board + " " + member + " " + replyCnt);
        }
    }

    @Test
    public void testGetRow() {
        Object[] row = boardRepository.getRow(3L);
        System.out.println(Arrays.toString(row));
    }

    @Test
    @Transactional // 자식삭제와 부모삭제가 같이 이루어져야함(부모삭제가 안될 시 자식 삭제를 막아야함)
    public void deleteTest() {
        // 자식(댓글) 삭제
        replyRepository.deleteByBno(49L);

        // 부모(원본글) 삭제
        boardRepository.deleteById(49L);
    }
}
