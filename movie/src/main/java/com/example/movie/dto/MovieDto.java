package com.example.movie.dto;

import java.time.LocalDateTime;

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
public class MovieDto {
    private Long mno;

    private String title;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
