package com.example.club.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.club.constant.ClubMemberRole;
import com.example.club.entity.ClubMember;

import jakarta.transaction.Transactional;

@SpringBootTest
public class ClubRepositoryTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClubMemberRepository clubMemberRepository;

    @Test
    public void testInsert() {
        // 1~80 : USER, 81~90 : USER,MANAGER, 91~100:USER,MANAGER,ADMIN

        IntStream.rangeClosed(1, 100).forEach(i -> {
            ClubMember clubMember = ClubMember.builder()
                    .email("user" + i + "@gmail.com")
                    .name("User" + i)
                    .fromSocial(false)
                    .password(passwordEncoder.encode("1111"))
                    .build();

            clubMember.addMemberRole(ClubMemberRole.USER);

            if (i > 80) {
                clubMember.addMemberRole(ClubMemberRole.MANAGER);
            }

            if (i > 90) {
                clubMember.addMemberRole(ClubMemberRole.ADMIN);
            }

            clubMemberRepository.save(clubMember);

        });
    }

    // FetchType
    // EAGER : left outer join을 기본으로 실행
    // LAZY : select 2번으로 처리

    // 웹 개발 시 굳이 EAGER를 자주 사용하지 않음 : 처음부터 사용하지 않는 정보를 가지고 나올 필요 X

    // @OneToOne, @ManyToOne : EAGER인 것들은 LAZY로 변경

    @Test
    // @Transactional
    public void testFind() {
        ClubMember clubMember = clubMemberRepository.findByEmailAndFromSocial("user95@gmail.com", false).get();
        System.out.println(clubMember);
    }
}
