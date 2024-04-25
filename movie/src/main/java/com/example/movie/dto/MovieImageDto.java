package com.example.movie.dto;

import java.time.LocalDateTime;

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
public class MovieImageDto {
    private Long inum;

    private String uuid;

    private String imgName;

    private String path;

    // Movie와의 관계
    private Movie movie;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
