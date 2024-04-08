package com.example.todo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TodoServiceTest {

    // servie가 잘만들어졌는지 test
    // Service <==> Repository 동작 확인
    @Autowired
    private TodoServiceImpl service;

    @Test
    public void serviceList() {
        System.out.println(service.getList());
    }
}
