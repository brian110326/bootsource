package com.example.jpa.repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.entity.Member;
import com.example.jpa.entity.RoleType;

@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertTest() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Member member = Member.builder().id("member" + i).age(30 + i).roleType(RoleType.USER)
                    .createdDate(LocalDateTime.now()).lastModifiedDate(LocalDateTime.now())
                    .userName("Mark")
                    .description("Description" + i)
                    .build();

            memberRepository.save(member);
        });

    }

    @Test
    public void readTest() {
        Optional<Member> result = memberRepository.findById("member10");

        System.out.println(result.get());

        System.out.println("============================");

        memberRepository.findAll().forEach(member -> {
            System.out.println(member);
        });

        System.out.println("========================================");
        // 특정이름을 조회
        memberRepository.findByUserName("Mark").forEach(member -> {
            System.out.println(member);
        });
    }

    @Test
    public void updateTest() {
        Optional<Member> result = memberRepository.findById("member10");

        result.ifPresent(member -> {
            member.setAge(55);
            member.setRoleType(RoleType.ADMIN);

            System.out.println(memberRepository.save(member));
        });
    }

    @Test
    public void deleteTest() {
        Optional<Member> result = memberRepository.findById("member9");

        result.ifPresent(member -> {
            memberRepository.delete(member);

            System.out.println("삭제된 member : " + result);
        });
    }
}
