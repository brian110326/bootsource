package com.example.movie.dto;

import java.time.LocalDateTime;

import com.example.movie.entity.Member;
import com.example.movie.entity.Movie;

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
public class ReviewDto {
    private Long reviewNo;

    private int grade;

    private String text;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;

    // Member와의 관계
    private Long mid;
    private String email;
    private String nickname;

    // Movie와의 관계
    private Long mno;
}
