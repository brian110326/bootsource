package com.example.jpa.repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.entity.Memo;

// JUnit : 테스트 라이브러리

@SpringBootTest // test 전용 클래스
public class MemoRepositoryTest {

    @Autowired // MemoRepository memoRepository = new MemoRepositoryImpl(MemoRepository 구현하는
               // 클래스) 자동으로 만들어줌
    private MemoRepository memoRepository;

    @Test // 테스트 메소드(return type : void)
    public void insertMemo() {
        // 100개의 메모를 입력

        for (int i = 1; i < 101; i++) {
            Memo memo = new Memo();
            memo.setMemoText("MemoText" + i);

            // save() : insert, update
            memoRepository.save(memo); // == dao.insert()
        }

    }

    @Test
    public void getMemo() {
        // 특정 아이디 메모 조회

        // Optional : null값을 체크할 수 있는 메소드를 보유하고 있음
        Optional<Memo> result = memoRepository.findById(21L);

        System.out.println(result.get());
    }

    @Test
    public void getMemoList() {
        // 전체 메모 조회

        // Optional : null값을 체크할 수 있는 메소드를 보유하고 있음
        List<Memo> list = memoRepository.findAll();

        for (Memo memo : list) {
            System.out.println(memo);
        }
    }

    @Test
    public void updateMemo() {
        // update할 entity를 찾기
        Optional<Memo> result = memoRepository.findById(25L);

        Memo memo = result.get();

        memo.setMemoText("update Title!");

        // save가 원본과 달라진 점을 찾아서 update로 함
        System.out.println(memoRepository.save(memo));

    }

    @Test
    public void deleteMemo() {
        // 삭제할 entity 찾기
        Optional<Memo> result = memoRepository.findById(24L);
        Memo memo = result.get();

        // 삭제
        memoRepository.delete(memo);
        System.out.println("삭제된 memo : " + memoRepository.findById(24L));
    }

    @Test
    public void queryMethodTest() {
        List<Memo> list = memoRepository.findByMnoLessThan(5L);
        System.out.println(list.size());

        List<Memo> list2 = memoRepository.findByMnoBetween(1L, 6L);
        list2.forEach(item -> {
            System.out.println(item);
        });
    }
}
