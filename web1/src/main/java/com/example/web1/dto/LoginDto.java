package com.example.web1.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
public class LoginDto {
    @Email(message = "이메일을 확인해주세요")
    @NotEmpty // @NotNull + 비어있는값 불가
    private String email;

    @Length(min = 2, max = 6)
    // @NotBlank // @NotEmpty + 비어있는값 불가
    private String name;
}
