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

    private String replyer; // 댓글작성자

    private Long bno;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
