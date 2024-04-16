package com.example.guestbook.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GuestBookDto {

    private Long gno;

    @NotBlank(message = "작성자 입력")
    private String writer;

    @NotBlank(message = "제목 입력")
    private String title;

    @NotBlank(message = "내용 입력")
    private String content;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
