package com.example.movie.entity;

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

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = { "member", "movie" })
public class Review extends BaseEntity {

    @Id
    @SequenceGenerator(name = "review_seq_gen", sequenceName = "review_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "review_seq_gen")
    private Long reviewNo;

    private int grade;

    private String text;

    // 1명의 회원이 여러개 리뷰작성
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    // 1개의 영화에 여러개 리뷰
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;
}
