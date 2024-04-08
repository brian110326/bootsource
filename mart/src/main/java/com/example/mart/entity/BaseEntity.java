package com.example.mart.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@EntityListeners(value = AuditingEntityListener.class)
@MappedSuperclass // BaseEntity를 상속받을 때 필드를 컬럼으로 인식하기
public abstract class BaseEntity {

    // 상속해야할 column들

    @CreatedDate
    private LocalDateTime createdDate; // 등록일

    @LastModifiedDate
    private LocalDateTime lastModifiedDate; // 수정일
}
