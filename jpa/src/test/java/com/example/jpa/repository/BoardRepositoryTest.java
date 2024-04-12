package com.example.jpa.repository;

import java.util.stream.LongStream;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

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

    @Test
    public void queryMethodTest() {
        List<Board> list = boardRepository.findList();
        System.out.println(list.size());
        // where b1_0.title=?
        // List<Board> list = boardRepository.findByTitle("Title");

        // System.out.println("findByTitle : " + list.size());

        // List<Board> list2 = boardRepository.findByTitleLike("Title");
        // System.out.println("findByTitleLike : " + list2.size());

        // List<Board> list3 = boardRepository.findByTitleStartingWith("Title");
        // System.out.println("findByTitleStartingWith : " + list3.size());

        // List<Board> list4 = boardRepository.findByTitleEndingWith("Title");
        // System.out.println("findByTitleEndingWith : " + list4.size());

        // List<Board> list5 = boardRepository.findByTitleContaining("Title");
        // System.out.println("findByTitleContaining : " + list5.size());

        // List<Board> list6 = boardRepository.findByWriterStartingWith("user");
        // System.out.println("findByWriterStartingWith " + list6.size());

        // where
        // b1_0.title like ? escape '\'
        // or b1_0.content=?
        // List<Board> list = boardRepository.findByTitleContainingOrContent("Title",
        // "Content");
        // System.out.println(list.size());

        // where
        // b1_0.title like ? escape '\'
        // or b1_0.content like ? escape '\'
        // List<Board> list2 =
        // boardRepository.findByTitleContainingOrContentContaining("Title", "Content");
        // System.out.println(list2.size());

        // List<Board> list =
        // boardRepository.findByTitleContainingAndIdGreaterThan("Title", 50L);
        // System.out.println(list.size());

        // List<Board> list2 = boardRepository.findByIdGreaterThanOrderByIdDesc(50L);
        // System.out.println(list2.size());

        // PageRequest.of(페이지번호, 페이지당 게시물 수); , 페이지번호는 0부터 시작
        // Pageable pageable = PageRequest.of(0, 10);
        // List<Board> list = boardRepository.findByIdGreaterThanOrderByIdDesc(0L,
        // pageable);
        // list.forEach(System.out::println);
    }
}
