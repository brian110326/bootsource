package com.example.mart.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.example.mart.entity.Item;
import com.example.mart.entity.Member;
import com.example.mart.entity.Order;
import com.example.mart.entity.QItem;
import com.example.mart.entity.QMember;
import com.example.mart.entity.QOrder;
import com.example.mart.entity.QOrderItem;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;

public class QueryDslOrderRepositoryImpl extends QuerydslRepositorySupport implements QueryDslOrderRepository {

    public QueryDslOrderRepositoryImpl() {
        super(Order.class);
    }

    @Override
    public List<Object[]> joinList() {

        // Q객체 가져오기
        QOrder order = QOrder.order;
        QMember member = QMember.member;
        QOrderItem orderItem = QOrderItem.orderItem;

        // 쿼리문 생성 1. JPAQuery 2. JPQLQuery
        // select * from order o join member m on o.member_id = m.member_id
        JPQLQuery<Order> query = from(order);

        // join : inner, left, right, full
        // join(조인대상, 별칭으로 사용할 쿼리타입)
        // on o.member_id = m.member_id 같은 뜻
        // query.innerJoin(order.member, member);
        query.join(order.member, member).leftJoin(order.orderItems, orderItem);
        // select m, t
        JPQLQuery<Tuple> tuple = query.select(order, member, orderItem);
        List<Tuple> result = tuple.fetch();

        System.out.println(result);

        // List<Tuple> ==> List<Object[]>

        List<Object[]> list = result.stream().map(t -> t.toArray()).collect(Collectors.toList());

        return list;
    }

    @Override
    public List<Member> members() {

        QMember member = QMember.member;

        // select * from member where name = 'user1' order by name desc
        JPQLQuery<Member> query = from(member);

        query.where(member.name.eq("user1")).orderBy(member.name.desc());

        JPQLQuery<Member> tuple = query.select(member);

        List<Member> list = tuple.fetch();

        return list;
    }

    @Override
    public List<Item> items() {
        // select * from item where name='바지' and price>20000
        QItem item = QItem.item;
        JPQLQuery<Item> query = from(item);
        query.where(item.name.eq("바지").and(item.price.gt(20000)));

        JPQLQuery<Item> tuple = query.select(item);
        List<Item> list = tuple.fetch();

        return list;
    }

}
