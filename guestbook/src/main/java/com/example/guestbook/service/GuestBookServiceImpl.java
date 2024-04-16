package com.example.guestbook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.guestbook.dto.GuestBookDto;
import com.example.guestbook.dto.PageRequestDto;
import com.example.guestbook.dto.PageResultDto;
import com.example.guestbook.entity.GuestBook;
import com.example.guestbook.entity.QGuestBook;
import com.example.guestbook.repository.GuestBookRepository;
import com.querydsl.core.BooleanBuilder;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GuestBookServiceImpl implements GuestBookService {

    private final GuestBookRepository guestBookRepository;

    // @Override
    // public List<GuestBookDto> getList() {
    // List<GuestBook> entities =
    // guestBookRepository.findAll(Sort.by("gno").descending());
    // List<GuestBookDto> list = new ArrayList<>();

    // entities.forEach(entity -> {
    // list.add(entityToDto(entity));
    // });

    // return list;

    // // Function<GuestBook,GuestBookDto> fn = (entity -> entityToDto(entity));
    // // return entities.stream().map(fn).collect(Collectors.toList());

    // }

    @Override
    public PageResultDto<GuestBookDto, GuestBook> getList(PageRequestDto requestDto) {
        // 1. PageRequestDto, PageResultDto만들기
        // 2. getList부분 수정하기(paging처리가 되는 findAll 호출)
        Pageable pageable = requestDto.getPageable(Sort.by("gno").descending());
        // Page<GuestBook> result = guestBookRepository.findAll(pageable);

        BooleanBuilder builder = getSearch(requestDto);

        Page<GuestBook> result = guestBookRepository.findAll(builder, pageable);

        Function<GuestBook, GuestBookDto> fn = (entity -> entityToDto(entity));
        return new PageResultDto<GuestBookDto, GuestBook>(result, fn);
    }

    @Override
    public GuestBookDto getRow(Long gno) {
        GuestBook guestBook = guestBookRepository.findById(gno).get();

        return entityToDto(guestBook);
    }

    @Override
    public Long update(GuestBookDto dto) {
        GuestBook guestBook = guestBookRepository.findById(dto.getGno()).get();
        guestBook.setTitle(dto.getTitle());
        guestBook.setContent(dto.getContent());
        guestBookRepository.save(guestBook);

        return entityToDto(guestBook).getGno();
    }

    @Override
    public void delete(Long gno) {
        guestBookRepository.deleteById(gno);
    }

    @Override
    public Long create(GuestBookDto dto) {
        GuestBook guestBook = dtoToEntity(dto);
        guestBook.setContent(dto.getContent());
        guestBook.setTitle(dto.getTitle());
        guestBook.setWriter(dto.getWriter());

        guestBookRepository.save(guestBook);

        return guestBook.getGno();
    }

    // Book 프로젝트에서는 BookRepository에서 작성함
    private BooleanBuilder getSearch(PageRequestDto requestDto) {
        BooleanBuilder builder = new BooleanBuilder();

        // Q클래스 사용
        QGuestBook guestBook = QGuestBook.guestBook;

        // type, keyword 가져오기
        String type = requestDto.getType();
        String keyword = requestDto.getKeyword();

        // gno > 0
        // where gno > 0 and title like ~~ or content like ~~
        builder.and(guestBook.gno.gt(0L));

        if (type == null || type.trim().length() == 0) {
            return builder;
        }

        BooleanBuilder conditionBuilder = new BooleanBuilder();

        if (type.contains("t")) {
            conditionBuilder.or(guestBook.title.containsIgnoreCase(keyword));
        }

        if (type.contains("c")) {
            conditionBuilder.or(guestBook.content.contains(keyword));
        }

        if (type.contains("w")) {
            conditionBuilder.or(guestBook.writer.contains(keyword));
        }

        builder.and(conditionBuilder);

        return builder;
    }

}
