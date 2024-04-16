package com.example.guestbook.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.guestbook.dto.GuestBookDto;
import com.example.guestbook.dto.PageRequestDto;
import com.example.guestbook.dto.PageResultDto;
import com.example.guestbook.entity.GuestBook;

@SpringBootTest
public class GuestBookServiceTest {

    @Autowired
    private GuestBookService service;

    @Test
    public void testList() {
        PageRequestDto requestDto = PageRequestDto.builder().size(10).page(1).type("tc").keyword("11111").build();

        PageResultDto<GuestBookDto, GuestBook> result = service.getList(requestDto);

        System.out.println("prev : " + result.isPrev());
        System.out.println("next : " + result.isNext());
        System.out.println("total : " + result.getTotalPage());
        result.getPageList().forEach(System.out::println);

        // 목록 확인
        result.getDtoList().forEach(System.out::println);
    }
}
