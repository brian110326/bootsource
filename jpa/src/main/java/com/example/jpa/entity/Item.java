package com.example.jpa.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "itemtbl")
public class Item {

    @Id
    @SequenceGenerator(name = "item_seq_gen", sequenceName = "item_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq_gen")
    private Long id;

    @Column(length = 50)
    private String itemNm;

    private Integer price;

    private Integer stockNumber;

    private String itemDetail;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime regTime;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime updateTime;
}
