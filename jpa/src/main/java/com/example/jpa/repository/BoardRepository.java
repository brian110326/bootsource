package com.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa.entity.Board;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // id로 찾기(findById), 전체 찾기(findAll)

    // title로 찾기
    List<Board> findByTitle(String title);

    // writer로 찾기
    List<Board> findByWriter(String writer);

    // like
    List<Board> findByTitleLike(String title);

    List<Board> findByTitleStartingWith(String title);

    List<Board> findByTitleEndingWith(String title);

    List<Board> findByTitleContaining(String title);

    // writer가 user로 시작하는 회원 조회
    List<Board> findByWriterStartingWith(String writer);

}
