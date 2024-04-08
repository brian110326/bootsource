package com.example.todo.repository;

import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.todo.entity.Todo;

@SpringBootTest
public class TodoRepositoryTest {

    // DAO == TodoRepository
    // service에서 호출하는 메소드 테스트

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void insertTest() {

        // Todo todo1 =
        // Todo.builder().completed(true).important(true).title("title1").build();
        // Todo todo2 =
        // Todo.builder().completed(false).important(false).title("title2").build();
        // Todo todo3 =
        // Todo.builder().completed(true).important(false).title("title3").build();
        // Todo todo4 =
        // Todo.builder().completed(false).important(true).title("title4").build();

        // todoRepository.save(todo1);
        // todoRepository.save(todo2);
        // todoRepository.save(todo3);
        // todoRepository.save(todo4);

        Todo todo1 = Todo.builder().title("title1").build();
        Todo todo2 = Todo.builder().title("title2").build();
        Todo todo3 = Todo.builder().title("title3").build();
        Todo todo4 = Todo.builder().title("title4").build();

        todoRepository.save(todo1);
        todoRepository.save(todo2);
        todoRepository.save(todo3);
        todoRepository.save(todo4);

        // insert
        // into
        // todotbl
        // (created_date, last_modified_date, title, todo_id)
        // values
        // (?, ?, ?, ?)

    }

    @Test
    public void getList() {
        todoRepository.findAll().forEach(todo -> {
            System.out.println(todo);
        });
    }

    @Test
    public void getRow() {
        Todo todo = todoRepository.findById(1L).get();
        System.out.println(todo);
    }

    // 완료목록만 조회
    @Test
    public void getCompletedList() {
        todoRepository.findByCompleted(true).forEach(todo -> {
            System.out.println(todo);
        });

        System.out.println("================================");

        todoRepository.findByImportant(true).forEach(todo -> {
            System.out.println(todo);
        });
    }

    @Test
    public void updateTest() {
        Todo todo = todoRepository.findById(1L).get();
        todo.setCompleted(true);
        todo.setTitle("TITLE1");

        todoRepository.save(todo);
    }

    @Test
    public void deleteTest() {
        todoRepository.deleteById(8L);
    }
}
