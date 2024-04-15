package com.example.book.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import lombok.Data;

// java Generics
// PageResultDto<DTO, EN> : ~DTO, ~Entiy 객체 담는 구조
// Box<T>

@Data
public class PageResultDto<DTO, EN> {
    private List<DTO> dtoList;

    public PageResultDto(Page<EN> result, Function<EN, DTO> fn) {
        // entity를 dto로 변환시키는 작업
        dtoList = result.stream().map(fn).collect(Collectors.toList());
    }
}
