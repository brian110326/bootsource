package com.example.board.dto;

import java.time.LocalDateTime;

import com.example.board.constant.MemberRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    @Email(message = "이메일 형식이 아닙니다")
    @NotBlank(message = "이메일 입력")
    private String email;

    @NotBlank(message = "비밀번호 입력")
    private String password;

    @NotBlank(message = "이름 입력")
    private String name;

    private MemberRole memberRole;

}
