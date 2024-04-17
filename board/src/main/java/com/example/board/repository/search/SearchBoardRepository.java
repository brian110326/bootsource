package com.example.board.repository.search;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchBoardRepository {

    // @Query(select m, t from Member m join m.team t = ?1)
    // 전체조회 시 board, member, reply 정보를 모두 조회 ==> Object[]
    List<Object[]> list();

    Object[] getRow(Long bno);
}
