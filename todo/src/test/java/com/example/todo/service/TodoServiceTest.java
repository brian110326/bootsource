package com.example.todo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.todo.dto.TodoDto;

@SpringBootTest
public class TodoServiceTest {

    // servie가 잘만들어졌는지 test
    // Service <==> Repository 동작 확인
    @Autowired
    private TodoService service;

    @Test
    public void serviceList() {
        System.out.println(service.getList());
    }

    @Test
    public void insertTest() {
        TodoDto dto = new TodoDto();
        dto.setTitle("Title5");
        dto.setImportant(true);
        System.out.println(service.create(dto));
    }

    @Test
    public void serviceRead() {
        System.out.println(service.getTodo(10L));
    }

    @Test
    public void completedListTest() {
        System.out.println(service.getCompletedList());
    }

    @Test
    public void deleteTest() {
        service.delete(13L);
    }
}
