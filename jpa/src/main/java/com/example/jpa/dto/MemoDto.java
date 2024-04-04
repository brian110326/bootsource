package com.example.jpa.dto;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "메모내용을 입력해주세요")
    private String memoText;
}
