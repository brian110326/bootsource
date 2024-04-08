package com.example.jpa.repository;

import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.entity.Locker;
import com.example.jpa.entity.SportsMember;

@SpringBootTest
public class LockerRepositoryTest {

    @Autowired
    private SportsMemberRepository sportsMemberRepository;

    @Autowired
    private LockerRepository lockerRepository;

    @Test
    public void insertTest() {
        // Locker부터 삽입
        LongStream.rangeClosed(1, 3).forEach(i -> {
            Locker locker = Locker.builder().name("locker" + i).build();

            lockerRepository.save(locker);
        });

        LongStream.rangeClosed(7, 9).forEach(i -> {
            SportsMember sportsMember = SportsMember.builder().name("user" + i).locker(Locker.builder().id(i).build())
                    .build();

            sportsMemberRepository.save(sportsMember);
        });

    }

    @Test
    public void updateTest() {
        SportsMember sportsMember = sportsMemberRepository.findById(6L).get();
        sportsMember.setName("Brian");
        sportsMemberRepository.save(sportsMember);
    }

    @Test
    public void readTest() {
        // 회원 조회 후 locker 정보 조회
        SportsMember member = sportsMemberRepository.findById(1L).get();
        System.out.println(member);
        System.out.println(member.getLocker());
    }

    @Test
    public void lockerMemberGet() {
        Locker locker = lockerRepository.findById(1L).get();
        System.out.println(locker);

        System.out.println(locker.getSportsMember());
    }
}
