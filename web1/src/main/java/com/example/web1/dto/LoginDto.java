package com.example.web1.dto;

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
    @NotEmpty // @NotNull + 비어있는값 불가
    private String email;

    @NotBlank // @NotEmpty + 비어있는값 불가
    private String name;
}
