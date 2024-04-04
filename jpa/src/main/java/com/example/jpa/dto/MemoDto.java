package com.example.jpa.dto;

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
public class MemoDto {
    // Memo Entity로 계속 사용가능하지만 구별 필요
    private Long mno;
    private String memoText;
}
