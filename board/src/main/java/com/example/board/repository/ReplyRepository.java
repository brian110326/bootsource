package com.example.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.board.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    // bno를 이용해서 reply 삭제
    // @Query("delete from Reply r where r.board.bno = ?1")
    @Query("delete from Reply r where r.board.bno = :bno")
    @Modifying // delete, update 구문 작성 시 같이 써야함(select 전용이기 때문)
    void deleteByBno(Long bno);

}
