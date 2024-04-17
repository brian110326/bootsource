package com.example.board.entity;

import jakarta.persistence.Entity;
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
@ToString
public class Reply extends BaseEntity {

    @Id
    @SequenceGenerator(name = "reply_seq_gen", sequenceName = "reply_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reply_seq_gen")
    private Long rno;

    private String text; // 댓글내용

    private String replyer; // 댓글작성자

    @ManyToOne
    private Board board;
}
