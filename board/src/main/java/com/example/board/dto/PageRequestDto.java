package com.example.board.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class PageRequestDto {
    private int page;
    private int size;

    // 검색
    // 초기화 안하면 null값
    private String type;
    private String keyword;

    public PageRequestDto() {
        this.page = 1;
        this.size = 10;

        this.type = "";
        this.keyword = "";
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page - 1, size, sort);
    }
}
