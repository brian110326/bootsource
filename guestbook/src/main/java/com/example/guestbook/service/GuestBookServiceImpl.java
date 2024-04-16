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
import com.example.guestbook.repository.GuestBookRepository;

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
        Page<GuestBook> result = guestBookRepository.findAll(pageable);

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

}
