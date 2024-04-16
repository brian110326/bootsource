package com.example.guestbook.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

        // Function<GuestBook,GuestBookDto> fn = (entity -> entityToDto(entity));
        // return entities.stream().map(fn).collect(Collectors.toList());

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

}
