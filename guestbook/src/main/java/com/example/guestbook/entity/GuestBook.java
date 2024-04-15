package com.example.guestbook.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class GuestBook extends BaseEntity {

    @Id
    @SequenceGenerator(name = "guestbook_seq_gen", sequenceName = "guestbook_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "guestbook_seq_gen")
    private Long gno;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;
}
