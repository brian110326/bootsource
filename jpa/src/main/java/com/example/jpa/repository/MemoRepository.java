package com.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.jpa.entity.Memo;

// JpaRepository<Entity, Entity에서 기본키의 타입>
public interface MemoRepository extends JpaRepository<Memo, Long> {
    // DAO 역할
}
