package com.example.board.dto;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.Data;

// java Generics
// PageResultDto<DTO, EN> : ~DTO, ~Entiy 객체 담는 구조
// Box<T>

@Data
public class PageResultDto<DTO, EN> {
    // 화면에 보여줄 목록
    private List<DTO> dtoList;

    // 총 페이지 번호
    private int totalPage;

    // 현재 페이지 번호
    private int page;

    // 목록 크기(한 페이지에 보여줄 게시물 수)
    private int size;

    // 시작페이지 번호, 끝페이지 번호
    private int start, end;

    private boolean prev, next;

    // 페이지 번호 목록
    private List<Integer> pageList;

    public PageResultDto(Page<EN> result, Function<EN, DTO> fn) {
        // entity를 dto로 변환시키는 작업
        dtoList = result.stream().map(fn).collect(Collectors.toList());

        // 총 개수 / 한페이지당 보여줄 게시물 수
        totalPage = result.getTotalPages();
        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable) {
        // pageable.getPageNumber() : 사용자가 요청한 페이지 번호(페이지 번호가 0부터 시작)
        // 실제 페이지는 1부터 시작하니 + 1
        this.page = pageable.getPageNumber() + 1;

        // 한 페이지당 보여줄 게시물 수
        this.size = pageable.getPageSize();

        int tempEnd = (int) (Math.ceil(page / 10.0)) * 10;
        this.start = tempEnd - 9;
        this.prev = start > 1;

        // 31~36페이지가 있는데 자동으로 40까지 계산함 => 36p가 마지막이어야함
        this.end = totalPage > tempEnd ? tempEnd : totalPage;
        this.next = totalPage > tempEnd;

        // int값으로 예를 들어 1~10 생성 ==> List<Integer> list에 담아야함
        // boxed() : int형을 ==> Integer로 바꿔줌(pageList가 List<Integer>형태이기 때문에)
        this.pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }

    // 전체 게시물 수 : 360개
    // 한 페이지에 10개씩 보여준다면 : 전체 필요 페이지 수 36p
}
