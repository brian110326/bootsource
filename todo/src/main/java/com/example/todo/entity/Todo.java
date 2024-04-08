package com.example.todo.entity;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "todotbl")
public class Todo extends BaseEntity {

    @Id
    @Column(name = "todo_id")
    @SequenceGenerator(name = "todo_seq_gen", sequenceName = "todo_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todo_seq_gen")
    private Long id;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Boolean completed;

    @Column(nullable = false)
    @ColumnDefault("0")
    private Boolean important;

    @Column(nullable = false)
    private String title;
}
