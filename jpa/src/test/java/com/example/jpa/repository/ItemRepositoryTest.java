package com.example.jpa.repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Local;

import com.example.jpa.entity.Item;
import com.example.jpa.entity.ItemSellStatus;

@SpringBootTest
public class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void createTest() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Item item = Item.builder().itemNm("운동화" + i).price(95000 * i).stockNumber(30)
                    .itemSellStatus(ItemSellStatus.SELL)
                    .regTime(LocalDateTime.now())
                    .updateTime(LocalDateTime.now())
                    .build();

            itemRepository.save(item);
        });
    }

    @Test
    public void readTest() {
        System.out.println(itemRepository.findById(8L));

        System.out.println("=============================");

        // 전체
        itemRepository.findAll().forEach(item -> {
            System.out.println(item);
        });
    }

    @Test
    public void updateTest() {
        Optional<Item> result = itemRepository.findById(8L);

        result.ifPresent(item -> {
            item.setItemNm("Jordan");
            item.setPrice(1300000);

            System.out.println(itemRepository.save(item));

        });
    }

    @Test
    public void deleteTest() {
        Optional<Item> result = itemRepository.findById(4L);

        result.ifPresent(item -> {
            itemRepository.delete(item);
            System.out.println("삭제된 item : " + result);
        });
    }

}
