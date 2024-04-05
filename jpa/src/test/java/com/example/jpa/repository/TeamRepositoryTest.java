package com.example.jpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpa.entity.Team;
import com.example.jpa.entity.TeamMember;

@SpringBootTest
public class TeamRepositoryTest {

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void insertTest() {

        // team 정보 삽입
        Team team1 = teamRepository.save(Team.builder().id("team1").name("팀1").build());
        Team team2 = teamRepository.save(Team.builder().id("team2").name("팀2").build());
        Team team3 = teamRepository.save(Team.builder().id("team3").name("팀3").build());

        // 회원 정보 삽입
        teamMemberRepository.save(TeamMember.builder().id("member1").username("홍길동").team(team1).build());
        teamMemberRepository.save(TeamMember.builder().id("member2").username("성춘향").team(team2).build());
        teamMemberRepository.save(TeamMember.builder().id("member3").username("Brian").team(team2).build());
        teamMemberRepository.save(TeamMember.builder().id("member4").username("Emily").team(team3).build());
    }

    // 연관관계가 있는 데이터 조회
    // 1. N:1(멤버:팀) 관계에서는 1에 해당하는 엔티티 정보를 가지고 나온다 => FetchType.EAGER 기본값
    // ==> 멤버를 조회하면 팀 정보도 같이 조회(객체 그래프 탐색으로 접근 가능)
    // ==> sql쿼리가 아닌 객체지향쿼리 작성

    // 2. 1:N(팀:멤버) 관계에서는 N에 해당하는 엔티티를 가지고 나오지 않음
    // ==> FetchType.LAZY 기본값

    // FetchType : 연결관계에서 상대 정보를 같이 가지고 나올건지 여부
    // FetchType.EAGER : 가지고 나옴
    // FetchType.LAZY : 가지고 나오지 않음

    @Test
    public void getRowTest() {
        // select
        // t1_0.team_name,
        // tm1_0.username
        // from
        // team_member tm1_0
        // left join
        // team t1_0
        // on t1_0.team_id=tm1_0.team_team_id
        // where
        // tm1_0.member_id=?

        // team_member(N) : team(1) => 외래키 제약조건
        // member를 찾을 때 N:1 관계에서는 1에 해당하는 정보도 기본으로 가지고온다
        // ==> join 필요(left join)
        TeamMember teamMember = teamMemberRepository.findById("member3").get();

        System.out.println(teamMember); // TeamMember(id=member3, username=Brian, team=Team(id=team2, name=팀2))

        // 객체 그래프 탐색
        System.out.println(teamMember.getTeam());
        System.out.println(teamMember.getTeam().getName());

        System.out.println("===================================================");

        // 회원 조회 시 나와 같은 팀에 소속된 회원과 팀 조회
        teamMemberRepository.findByMemberEqualTeam("팀2").forEach(member -> {
            System.out.println(member);
        });
    }

    @Test
    public void updateTest() {
        // 멤버의 팀 변경
        TeamMember member = teamMemberRepository.findById("member3").get();

        Team team = Team.builder().id("team3").build();
        member.setTeam(team);

        System.out.println("수정 후 : " + teamMemberRepository.save(member));
    }

    @Test
    public void delteTest() {
        // 팀원 먼저 삭제 or 팀원 팀을 null로 설정하기
        TeamMember member = teamMemberRepository.findById("member1").get();
        member.setTeam(null);
        teamMemberRepository.save(member);

        // 팀 삭제
        teamRepository.deleteById("team1");
    }

    // OneToMany 설정 기준으로
    // 팀을 기준으로 회원 조회 가능여부
    // @Transactional
    @Test
    public void getMemberList() {

        // select
        // t1_0.team_id,
        // t1_0.team_name
        // from
        // team t1_0
        // where
        // t1_0.team_id=?
        Team team = teamRepository.findById("team3").get();

        // LazyInitializationException : ToString() => members도 같이 찍기 때문에 오류
        System.out.println(team);

        // select
        // m1_0.member_id,
        // m1_0.username
        // from
        // team_member m1_0
        // where
        // m1_0.team_team_id=?

        // LazyInitializationException 해결
        // 1.@Transactional(transaction : 한번에 일괄 처리)
        // 2.FetchType.EAGER 로 변경하기(OneToMany annotation에 추가하기)

        // @Transactional : member도 같이 한꺼번에 가져와줘
        team.getMembers().forEach(member -> {
            System.out.println(member);
        });
    }
}
