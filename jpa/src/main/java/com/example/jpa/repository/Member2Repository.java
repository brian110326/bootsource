package com.example.jpa.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.jpa.entity.Member2;
import com.example.jpa.entity.Team2;

public interface Member2Repository extends JpaRepository<Member2, Long> {

    // jpql 사용 시
    // 1. entity 타입 결과 => List<Entity명>
    // 2. 개별 타입 결과 => List<Object[]>

    @Query("select m from Member2 m")
    List<Member2> findByMembers(Sort sort);

    @Query("select m.userName, m.age from Member2 m")
    // String, Integer 등 개별로 가져오면 Member2타입으로 불가능
    List<Object[]> findByMembers2();

    // 특정 나이보다 많은 회원 조회
    @Query("select m from Member2 m where m.age > ?1")
    List<Member2> findByAgeList(int id);

    // 특정 팀의 회원 조회
    @Query("select m from Member2 m where m.team2 = ?1")
    List<Member2> findByTeamEqual(Team2 team2);
}
