package com.example.mart.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.mart.entity.Delivery;
import com.example.mart.entity.DeliveryStatus;
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

    @Autowired
    private DeliveryRepository deliveryRepository;

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

    @Test
    public void deleteTest() {
        // 주문(부모) 제거 시 주문 아이템도(자식) 같이 제거 가능?
        // 주문 조회시 주문 아이템 조회 가능?
        // orderRepository.deleteById(1L); ==> 주문아이템이 아직 존재하기 때문에 error

        // 주문아이템 제거 -> 주문 제거
        orderItemRepository.delete(OrderItem.builder().id(1L).build());
        orderRepository.deleteById(1L);
    }

    @Test
    // @Transactional
    public void readTest2() {
        // @OneToMany를 이용해 조회 => 관련있는 엔티티를 처음부터 가지고 오지 않음
        // Order : OrderItem

        Order order = orderRepository.findById(1L).get();
        System.out.println(order); // error발생

        // Order를 기준으로 OrderItem 조회
        // 1) Transacional
        // 2) FetchType.EAGER로 변경
        System.out.println(order.getOrderItems());

    }

    @Test
    @Transactional
    public void readTest3() {
        // @OneToMany를 이용해 조회 => 관련있는 엔티티를 처음부터 가지고 오지 않음
        // Member : Order
        // 멤버를 통해 해당 주문내역을 조회

        Member member = memberRepository.findById(1L).get();
        System.out.println(member);

        System.out.println(member.getOrders());
    }

    @Test
    public void orderInsertDeliveryTest() {
        // 누가 주문하는지
        Member member = Member.builder().id(1L).build();
        // 어떤 상품을 주문하는지
        Item item = Item.builder().id(2L).build();

        // 배송지 입력
        Delivery delivery = Delivery.builder().city("NY").street("55powerStreet").zipcode("09876")
                .deliveryStatus(DeliveryStatus.READY)
                .build();
        deliveryRepository.save(delivery);

        // 주문 + 주문상품
        Order order = Order.builder().member(member).orderDate(LocalDateTime.now()).orderStatus(OrderStatus.ORDER)
                .delivery(delivery)
                .build();

        orderRepository.saveAndFlush(order);

        OrderItem orderItem = OrderItem.builder().item(item).order(order).orderPrice(20000).count(2).build();

        orderItemRepository.save(orderItem);

    }

    @Test
    public void orderView() {
        Order order = orderRepository.findById(2L).get();
        System.out.println(order);

        System.out.println(order.getDelivery().getCity());
        System.out.println(order.getDelivery().getStreet());
        System.out.println(order.getDelivery().getZipcode());

    }

    @Test
    public void deliveryOrderGet() {
        // delivery쪽에서 order를 조회하기
        Delivery delivery = deliveryRepository.findById(1L).get();
        System.out.println(delivery);

        System.out.println(delivery.getOrder());
    }

    @Test
    @Transactional
    public void testJoinTest() {
        List<Object[]> list = orderRepository.joinList();

        System.out.println(list);
        // [[Order(id=2, member=Member(id=1, name=UserName1, zipcode=Zipcode1,
        // city=Boston, street=Street1), orderDate=2024-04-08T10:17:02.350265,
        // orderStatus=ORDER), Member(id=1, name=UserName1, zipcode=Zipcode1,
        // city=Boston, street=Street1)], [Order(id=1, member=Member(id=1,
        // name=UserName1, zipcode=Zipcode1, city=Boston, street=Street1),
        // orderDate=2024-04-05T15:22:18.177321, orderStatus=CANCEL), Member(id=1,
        // name=UserName1, zipcode=Zipcode1, city=Boston, street=Street1)]]

        for (Object[] objects : list) {
            Order order = (Order) objects[0];
            Member member = (Member) objects[1];
            OrderItem orderItem = (OrderItem) objects[2];

            System.out.println("========================== test 메소드==================");
            System.out.println(order);
            System.out.println(member);
            System.out.println(orderItem);
        }
    }
}
