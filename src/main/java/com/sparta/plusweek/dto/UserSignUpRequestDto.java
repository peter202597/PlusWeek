package com.sparta.plusweek.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserSignUpRequestDto {
    @Pattern(regexp = "^[a-zA-Z0-9]{3,}$")
    private String nickname;
    @Size(min = 4)
    private String password;
    private String confirmPassword;
}

