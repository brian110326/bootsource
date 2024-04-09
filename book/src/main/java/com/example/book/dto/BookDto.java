package com.example.book.dto;

import com.example.book.entity.Category;
import com.example.book.entity.Publisher;

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

    private String title;

    private String writer;

    private Integer price;

    private Integer salePrice;

    private Category category;

    private Publisher publisher;
}
