package com.example.movie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.movie.entity.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 1) findBy~
    // 2) findBy + @Query
    // 3) 동적 쿼리 : JPQL

    // email unique를 걸었기 때문에 하나만 나옴(List X)
    Optional<Member> findByEmail(String email);
}
