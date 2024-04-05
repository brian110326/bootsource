package com.example.mart.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mart.entity.Item;
import com.example.mart.entity.Member;
import com.example.mart.entity.Order;
import com.example.mart.entity.OrderItem;
import com.example.mart.entity.OrderStatus;

@SpringBootTest
public class MartRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Test
    public void insertTest() {
        LongStream.rangeClosed(1, 3).forEach(i -> {
            Member member = Member.builder().name("UserName" + i).zipcode("Zipcode" + i).city("City" + i)
                    .street("Street" + i).build();

            memberRepository.save(member);
        });

        IntStream.rangeClosed(1, 3).forEach(i -> {
            Item item = Item.builder().name("ItemName" + i).price(25000 + i).stockQuantity(1000 + i)
                    .build();

            itemRepository.save(item);
        });
    }

    // 주문
    @Test
    public void orderInsertTest() {
        // 누가 주문하는지
        Member member = Member.builder().id(1L).build();
        // 어떤 상품을 주문하는지
        Item item = Item.builder().id(1L).build();

        // 주문 + 주문상품
        Order order = Order.builder().member(member).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.ORDER)
                .build();

        orderRepository.saveAndFlush(order);

        OrderItem orderItem = OrderItem.builder().item(item).order(order).orderPrice(20000).count(2).build();

        orderItemRepository.save(orderItem);

    }

    @Test
    public void readTest() {
        // 전체 회원 조회
        memberRepository.findAll().forEach(member -> {
            System.out.println(member);
        });

        // 전체 아이템 조회
        itemRepository.findAll().forEach(item -> {
            System.out.println(item);
        });

        // 주문 아이템 조회 시 주문정보 출력(OrderItem(N):Order(1))
        // 객체 그래프 탐색 => N:1에서 FetchType.EAGER 이기 때문에
        orderItemRepository.findAll().forEach(entity -> {
            System.out.println("상품정보 : " + entity.getItem());
            System.out.println("구매자정보 : " + entity.getOrder().getMember().getName());
        });

        // OrderItem(id=1, item=Item(id=1, name=ItemName1, price=25001,
        // stockQuantity=1001), order=Order(id=1, member=Member(id=1, name=UserName1,
        // zipcode=Zipcode1, city=City1, street=Street1),
        // orderDate=2024-04-05T15:22:18.177321, orderStatus=ORDER), orderPrice=20000,
        // count=2)

    }

    @Test
    public void updateTest() {
        // 멤버 주소 수정
        Member member = memberRepository.findById(1L).get();
        member.setCity("Boston");
        memberRepository.save(member);

        // 아이템 가격 수정
        Item item = itemRepository.findById(1L).get();
        item.setPrice(50000);
        itemRepository.save(item);

        // 주문상태 수정 CANCEL
        Order order = orderRepository.findById(1L).get();
        order.setOrderStatus(OrderStatus.CANCEL);
        orderRepository.save(order);
    }
}
