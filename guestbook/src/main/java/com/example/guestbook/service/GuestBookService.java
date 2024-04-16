package com.example.guestbook.service;

import java.util.List;

import com.example.guestbook.dto.GuestBookDto;
import com.example.guestbook.dto.PageRequestDto;
import com.example.guestbook.dto.PageResultDto;
import com.example.guestbook.entity.GuestBook;

public interface GuestBookService {
    // public List<GuestBookDto> getList();

    public PageResultDto<GuestBookDto, GuestBook> getList(PageRequestDto requestDto);

    public default GuestBookDto entityToDto(GuestBook entity) {
        GuestBookDto dto = GuestBookDto.builder().gno(entity.getGno()).writer(entity.getWriter())
                .title(entity.getTitle())
                .content(entity.getContent()).createdDate(entity.getCreatedDate())
                .lastModifiedDate(entity.getLastModifiedDate()).build();

        return dto;
    }

    public default GuestBook dtoToEntity(GuestBookDto dto) {
        GuestBook entity = GuestBook.builder().gno(dto.getGno()).writer(dto.getWriter())
                .title(dto.getTitle())
                .content(dto.getContent()).build();

        return entity;
    }

    public GuestBookDto getRow(Long gno);

    public Long update(GuestBookDto dto);

    public void delete(Long gno);

    public Long create(GuestBookDto dto);
}
