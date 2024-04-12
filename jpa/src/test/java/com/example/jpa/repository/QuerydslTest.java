package com.example.jpa.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import com.example.jpa.entity.Member2;
import com.example.jpa.entity.Product;
import com.example.jpa.entity.QMember2;
import com.example.jpa.entity.QProduct;
import com.querydsl.core.BooleanBuilder;

@SpringBootTest
public class QuerydslTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private Member2Repository member2Repository;

    @Test
    public void testQueryDsl() {

        // QProduct 가져오기
        QProduct product = QProduct.product;

        Iterable<Product> products = productRepository.findAll(product.name.eq("Product1"));

        // 제품명이 Product10이고 가격이 5000 초과인 제품 조회
        // gt : greater than
        products = productRepository.findAll(product.name.eq("Product10").and(product.price.gt(5000)));

        // 제품명이 Product10이고 가격이 5000 이상인 제품 조회
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(product.name.eq("Product10"));
        builder.and(product.price.goe(5000));
        products = productRepository.findAll(builder);

        // 제품명에 Product 글자가 포함된 제품 조회
        products = productRepository.findAll(product.name.contains("Product"));

        // 제품명에 Product 글자로 시작하는 제품 조회
        products = productRepository.findAll(product.name.startsWith("Product"));

        // 제품명에 Product 글자로 끝나는 제품 조회
        products = productRepository.findAll(product.name.endsWith("Product"));

        for (Product pro : products) {
            System.out.println(pro);
        }

    }

    @Test
    public void testQueryDsl2() {

        QMember2 member2 = QMember2.member2;

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(member2.userName.eq("User1"));

        Iterable<Member2> list = member2Repository.findAll(builder, Sort.by("id").descending());

        for (Member2 member : list) {
            System.out.println(member);
        }
    }
}
