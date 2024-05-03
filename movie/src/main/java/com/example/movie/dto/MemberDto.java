package com.example.movie.dto;

import java.time.LocalDateTime;

import com.example.movie.constant.MemberRole;

import jakarta.validation.constraints.Email;
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
public class MemberDto {
    private Long mid;

    @Email(message = "이메일 형식이 아닙니다")
    @NotBlank(message = "이메일 입력")
    private String email;

    @NotBlank(message = "비밀번호 입력")
    private String password;

    @NotBlank(message = "닉네임 입력")
    private String nickname;

    private MemberRole memberRole;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
