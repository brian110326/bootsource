package com.example.todo.entity;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

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

@DynamicInsert // default 동작 , not null 조건걸면 X
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

    // @Column(nullable = false)
    @ColumnDefault("0") // sql구문에서 default 값을 설정
    private Boolean completed;

    // @Column(nullable = false)
    @ColumnDefault("0")
    private Boolean important;

    @Column(nullable = false)
    private String title;
}

// default 값을 삽입 : 아무것도 입력이 되지 않으면 default 값으로 입력한다는 의미
// JPA에서는 default 값으로 자동 삽입하려면 @DynamicInsert가 필요
// @DynamicInsert : 데이터가 존재하는 필드만으로 insert sql문 생성 ==> not null이 아닌 필드만 해줌
