package com.example.book.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "books")
@Builder
@Entity
public class Publisher extends BaseEntity {

    @Id
    @Column(name = "publisher_id")
    @SequenceGenerator(name = "book_publisher_seq_gen", sequenceName = "publisher_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_publisher_seq_gen")
    private Long id;

    @Column(name = "publisher_name")
    private String name;

    @OneToMany(mappedBy = "publisher")
    @Builder.Default
    private List<Book> books = new ArrayList<>();
}
