package com.example.jpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        TeamMember teamMember = teamMemberRepository.findById("member3").get();
        System.out.println(teamMember); // TeamMember(id=member3, username=Brian, team=Team(id=team2, name=팀2))
    }
}
