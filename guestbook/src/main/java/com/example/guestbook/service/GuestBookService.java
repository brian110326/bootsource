package com.example.guestbook.service;

import java.util.List;

import com.example.guestbook.dto.GuestBookDto;

public interface GuestBookService {
    public List<GuestBookDto> getList();

    public GuestBookDto getRow(Long gno);
}
