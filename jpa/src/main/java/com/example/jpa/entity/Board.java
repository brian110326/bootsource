package com.example.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "boardtbl")
public class Board {

    @Id
    @SequenceGenerator(name = "board_seq_gen", sequenceName = "board_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq_gen")
    // Long type 이기때문에 자리수를 바꾸는건 불가능
    private Long id;

    @Column(length = 100, nullable = true)
    private String title;

    @Column(length = 1500, nullable = true)
    private String content;

    @Column(length = 50, nullable = true)
    private String writer;
}
