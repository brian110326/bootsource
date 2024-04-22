package com.example.board.service;

import java.util.List;

import com.example.board.dto.BoardDto;
import com.example.board.dto.ReplyDto;
import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.entity.Reply;

public interface ReplyService {

    List<ReplyDto> getReplise(Long bno);

    Long create(ReplyDto dto);

    void remove(Long rno);

    ReplyDto getReply(Long rno);

    Long update(ReplyDto dto);

    public default ReplyDto entityToDto(Reply reply) {
        ReplyDto dto = ReplyDto.builder().rno(reply.getRno()).text(reply.getText()).replyer(reply.getReplyer())
                .bno(reply.getBoard().getBno()).createdDate(reply.getCreatedDate())
                .lastModifiedDate(reply.getLastModifiedDate()).build();

        return dto;
    }

    public default Reply dtoToEntity(ReplyDto dto) {

        Board board = Board.builder().bno(dto.getBno()).build();

        Reply reply = Reply.builder().rno(dto.getRno()).text(dto.getText()).replyer(dto.getReplyer())
                .board(board).build();

        return reply;
    }
}
