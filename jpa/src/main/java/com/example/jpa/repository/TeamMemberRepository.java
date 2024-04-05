package com.example.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.jpa.entity.TeamMember;

public interface TeamMemberRepository extends JpaRepository<TeamMember, String> {

    // sql query가 아님(객체를 기준으로 작성해야 함)
    // m.team : m(별칭), m.team 테이블 => 별칭 t
    // ?1 : 1번 물음표
    // select m,t : m,t 테이블 정보 다 가져오기
    @Query("select m,t from TeamMember m join m.team t where t.name=?1")
    public List<TeamMember> findByMemberEqualTeam(String teamName);
}
