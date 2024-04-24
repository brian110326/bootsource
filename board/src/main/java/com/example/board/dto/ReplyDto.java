package com.example.board.dto;

import java.time.LocalDateTime;

import com.example.board.entity.Board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDto {
    private Long rno;

    private String text; // 댓글내용

    private String writerEmail; // 작성자 아이디(email)

    private String writerName;

    private Long bno;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
