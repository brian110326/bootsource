package com.example.jpa.repository;

import org.springframework.data.domain.Pageable;
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

    // title이 Title 키워드가 있거나
    // content가 Content 문자열 포함되어있는
    List<Board> findByTitleContainingOrContent(String title, String content);

    List<Board> findByTitleContainingOrContentContaining(String title, String content);

    // title이 Title 키워드가 있고,
    // id가 50보다 큰 게시물 조회
    List<Board> findByTitleContainingAndIdGreaterThan(String title, Long id);

    // id가 50보다 큰 게시물 조회 시 내림차순 정렬
    List<Board> findByIdGreaterThanOrderByIdDesc(Long id);

    List<Board> findByIdGreaterThanOrderByIdDesc(Long id, Pageable pageable);

}
