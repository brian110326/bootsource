package com.example.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

// 기본타입 : int, long, boolean, char, float, double : null X
// 객체타입 : Integer, Long, Boolean, String, Double.... : null 값 대입 가능

@Entity
public class Test {

    @Id
    private Long id;

    // 기본타입은 null값이 허용되지 않기 때문에 자동으로 not null을 붙여줌
    @Column(columnDefinition = "number(8)")
    private long id2;
    private int id3;

    private Integer id4;

    // create table test (
    // id number(19,0) not null,
    // id2 number(19,0) not null,
    // id3 number(10,0) not null,
    // id4 number(10,0),
    // primary key (id)
    // )
}
