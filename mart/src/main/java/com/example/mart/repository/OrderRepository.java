package com.example.mart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.example.mart.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>, QueryDslOrderRepository {

}
