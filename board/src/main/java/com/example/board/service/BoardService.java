package com.example.board.service;

import java.util.List;

import com.example.board.dto.BoardDto;
import com.example.board.entity.Board;
import com.example.board.entity.Member;

public interface BoardService {

    public List<BoardDto> getList();

    public BoardDto getRow(Long bno);

    public default BoardDto entityToDto(Board board, Member member, Long replyCount) {
        BoardDto dto = BoardDto.builder().bno(board.getBno()).writerEmail(member.getEmail())
                .writerName(member.getName()).replyCount(replyCount != null ? replyCount : 0)
                .title(board.getTitle())
                .content(board.getContent()).createdDate(board.getCreatedDate())
                .lastModifiedDate(board.getLastModifiedDate()).build();

        return dto;
    }

    public default Board dtoToEntity(BoardDto dto) {

        Member member = Member.builder().email(dto.getWriterEmail()).build();

        Board entity = Board.builder().bno(dto.getBno()).writer(member)
                .title(dto.getTitle())
                .content(dto.getContent()).build();

        return entity;
    }

}
