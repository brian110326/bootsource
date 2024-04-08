package com.example.jpa.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.jpa.entity.Child;
import com.example.jpa.entity.Parent;

@SpringBootTest
public class ParentRepositoryTest {

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private ChildRepository childRepository;

    @Test
    public void insertTest() {
        // 부모1명에 자식 2명

        Parent parent = Parent.builder().name("Parent").build();

        parentRepository.save(parent);

        LongStream.rangeClosed(1, 2).forEach(i -> {
            Child child = Child.builder().name("child" + i).parent(parent).build();

            childRepository.save(child);
        });
    }

    @Test
    public void insertCascadeTest() {
        // 부모1명에 자식 2명

        Parent parent = Parent.builder().name("Parent2").build();

        LongStream.rangeClosed(1, 2).forEach(i -> {
            Child child = Child.builder().name("child" + i).parent(parent).build();
            parent.getChildList().add(child);
            // childRepository.save(child);
        });

        parentRepository.save(parent);
        // cascade = CascadeType.ALL ==> childRepository.save안해도 child insert됨
        // cascade : 영속상태 전이 ==> 부모가 영속상태 시 자식도 같이 영속상태로
        // 종속되어있는 관계가 있다면 : cascade 적용가능

    }

    @Test
    public void deleteTest() {
        // 부모가 자식을 가지고있는 경우 : 삭제 시 자식의 부모 아이디 변경 후 부모 삭제 or 자식부터 삭제
        Parent parent = Parent.builder().id(1L).build();

        LongStream.rangeClosed(1, 2).forEach(i -> {
            Child child = Child.builder().id(i).build();

            childRepository.delete(child);

        });

        // Child child = Child.builder().id(i).parent(null).build();

        parentRepository.delete(parent);
    }

    @Test
    public void deleteCascadeTest() {

        Parent parent = Parent.builder().id(52L).build();

        LongStream.rangeClosed(1, 2).forEach(i -> {
            Child child = Child.builder().id(101 + i).parent(parent).build();
            parent.getChildList().add(child);

        });

        parentRepository.delete(parent);

    }

    @Test
    @Transactional
    public void deleteOrphanTest() {

        Parent parent = parentRepository.findById(2L).get();

        // FetchTpye이 LAZAY인 경우 오류발생
        // System.out.println(parent.getChildList());

        LongStream.rangeClosed(1, 2).forEach(i -> {
            Child child = Child.builder().name("child" + i).parent(parent).build();
            childRepository.save(child);
        });

        // parentRepository.save(parent);

    }
}
