package com.sparta.plusweek.domain.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

// 게시글 생성 요청 DTO
// 생성되기 전 암호화된 비밀번호로 변경이 필요
@Getter
public class PostCreateRequestDto {

    @NotBlank
    @Length(min = 1, max = 500)
    private String title;

    @NotBlank
    private String author;

    @NotBlank
    private String password;

    @NotBlank
    @Length(min = 1, max = 5000)
    private String contents;

    public void changeEncodedPassword(String encodedPassword) {
        this.password = encodedPassword;
    }
}
