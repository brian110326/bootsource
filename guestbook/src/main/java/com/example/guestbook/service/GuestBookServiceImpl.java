package com.example.guestbook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.guestbook.dto.GuestBookDto;
import com.example.guestbook.entity.GuestBook;
import com.example.guestbook.repository.GuestBookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GuestBookServiceImpl implements GuestBookService {

    private final GuestBookRepository guestBookRepository;

    @Override
    public List<GuestBookDto> getList() {
        List<GuestBook> entities = guestBookRepository.findAll(Sort.by("gno").descending());
        List<GuestBookDto> list = new ArrayList<>();

        entities.forEach(entity -> {
            list.add(entityToDto(entity));
        });

        return list;
    }

    private GuestBookDto entityToDto(GuestBook entity) {
        GuestBookDto dto = GuestBookDto.builder().gno(entity.getGno()).writer(entity.getWriter())
                .title(entity.getTitle())
                .content(entity.getContent()).createdDate(entity.getCreatedDate())
                .lastModifiedDate(entity.getLastModifiedDate()).build();

        return dto;
    }

    @Override
    public GuestBookDto getRow(Long gno) {
        GuestBook guestBook = guestBookRepository.findById(gno).get();

        return entityToDto(guestBook);
    }

}
