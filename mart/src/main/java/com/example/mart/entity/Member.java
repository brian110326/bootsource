package com.example.mart.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "orders")
@Table(name = "mart_member")
@Entity
public class Member {

    @Id
    @SequenceGenerator(name = "mart_member_seq_gen", sequenceName = "mart_member_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mart_member_seq_gen")
    private Long id;

    private String name;

    private String zipcode;

    private String city;

    private String street;

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER) // 반대편에 있는 Member 변수명
    @Builder.Default
    private List<Order> orders = new ArrayList<>();
}
