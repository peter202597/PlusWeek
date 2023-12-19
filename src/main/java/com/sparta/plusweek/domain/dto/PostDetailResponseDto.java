package com.sparta.plusweek.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

// 게시글 세부 정보를 응답하는 DTO
// 비밀번호는 들어가면 안 된다.
@Getter
@Setter
public class PostDetailResponseDto {

    private Long id;
    private String title;
    private String author;
    private String contents;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
