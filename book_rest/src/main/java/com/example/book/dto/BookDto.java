package com.example.book.dto;

import java.time.LocalDateTime;

import com.example.book.entity.Category;
import com.example.book.entity.Publisher;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BookDto {

    private Long id;

    @NotBlank(message = "제목입력")
    private String title;

    @NotBlank(message = "저자입력")
    private String writer;

    @NotNull(message = "가격입력")
    private Integer price;

    @NotNull(message = "할인가격입력")
    private Integer salePrice;

    @NotBlank(message = "카테고리 입력")
    private String categoryName;

    @NotBlank(message = "출판사 입력")
    private String publisherName;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
