package com.example.todo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {
    private Long id;

    private Boolean completed;

    private Boolean important;

    private String title;

    private LocalDateTime createdDate;

    private LocalDateTime lastModified;
}
