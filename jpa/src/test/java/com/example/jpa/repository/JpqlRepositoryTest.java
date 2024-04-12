package com.example.jpa.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import com.example.jpa.entity.Address;
import com.example.jpa.entity.Member2;
import com.example.jpa.entity.Order;
import com.example.jpa.entity.Product;
import com.example.jpa.entity.Team2;

import oracle.net.aso.b;

@SpringBootTest
public class JpqlRepositoryTest {

    @Autowired
    private Member2Repository member2Repository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Team2Repository team2Repository;

    @Test
    public void insertTest() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            Team2 team2 = Team2.builder().name("Team" + i).build();
            team2Repository.save(team2);

            Member2 member2 = Member2.builder().userName("User" + i).age(i).team2(team2).build();
            member2Repository.save(member2);

            Product product = Product.builder().name("Product" + i).price(i * 1000).stockAmount(i * 5).build();
            productRepository.save(product);
        });
    }

    @Test
    public void orderInsertTest() {
        Address address = new Address();
        address.setCity("Brooklyn NY");
        address.setStreet("PowerStreet50");
        address.setZipcode("78249");

        IntStream.rangeClosed(1, 3).forEach(i -> {
            Member2 member2 = Member2.builder().id(1L).build();
            Product product = Product.builder().id(2L).build();

            Order order = Order.builder().member2(member2).product(product).homeAddress(address).build();
            orderRepository.save(order);
        });
    }

    @Test
    public void findMembersTest() {
        // List<Member2> list = member2Repository.findByMembers();
        // list.forEach(member -> {
        // System.out.println(member);
        // });

        System.out.println(member2Repository.findByMembers(Sort.by("age")));

        List<Object[]> list = member2Repository.findByMembers2();

        for (Object[] objects : list) {
            System.out.println(Arrays.toString(objects));
            System.out.printf("userName : %s, age : %d\n", objects[0], objects[1]);
        }
    }

    @Test
    public void findAddressTest() {
        List<Address> list = orderRepository.findByHomeAddress();
        System.out.println(list);
    }
}
