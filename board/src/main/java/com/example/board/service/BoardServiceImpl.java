package com.example.board.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.board.dto.BoardDto;
import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Override
    public List<BoardDto> getList() {
        List<Object[]> result = boardRepository.list();

        Function<Object[], BoardDto> fn = (entity -> entityToDto((Board) entity[0], (Member) entity[1],
                (Long) entity[2]));

        List<BoardDto> list = result.stream().map(fn).collect(Collectors.toList());

        return list;
    }

    @Override
    public BoardDto getRow(Long bno) {
        Object[] objects = boardRepository.getRow(bno);

        return entityToDto((Board) objects[0], (Member) objects[1], bno);
    }

}
