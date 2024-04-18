package com.example.book.entity;

import com.fasterxml.jackson.databind.JsonSerializable.Base;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(exclude = { "category", "publisher" })
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book extends BaseEntity {

    @Id
    @Column(name = "book_id")
    @SequenceGenerator(name = "book_seq_gen", sequenceName = "book_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq_gen")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer salePrice;

    // fetchType : One으로 끝나면 EAGER
    // FetchType.LAZY 바꾸는 이유 : Book을 기준으로 조회할 때 category는 나오지 않게하기위해(오로지 Book만 조회)
    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Publisher publisher;
}
