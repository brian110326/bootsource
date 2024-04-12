package com.example.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa.entity.Memo;

// JpaRepository<Entity, Entity에서 기본키의 타입>
public interface MemoRepository extends JpaRepository<Memo, Long> {
    // DAO 역할

    // Id가 5보다 작은 메모 조회
    List<Memo> findByMnoLessThan(Long id);

    // mno가 10보다 작은 메모(내림차순)
    List<Memo> findByMnoLessThanOrderByMnoDesc(Long id);

    // mno >= 50 and mno <= 70
    List<Memo> findByMnoBetween(Long id, Long id2);
}
