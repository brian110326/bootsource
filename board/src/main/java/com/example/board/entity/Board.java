package com.example.board.entity;

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

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "writer")
public class Board extends BaseEntity {

    @Id
    @SequenceGenerator(name = "board_seq_gen", sequenceName = "board_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq_gen")
    private Long bno;

    private String title;

    private String content;

    // fetch 방식 : FetchType.EAGER(즉시 로딩)
    // ==> left join 방식으로 데이터 처리
    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer;
}
