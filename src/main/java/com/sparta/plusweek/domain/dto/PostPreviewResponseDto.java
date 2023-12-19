package com.sparta.plusweek.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

// 게시글 목록 반환을 위한 간소화된 게시글 정보 DTO
// 비밀번호는 들어가면 안 된다.
@Getter
@Setter
public class PostPreviewResponseDto {

    private Long id;
    private String title;
    private String author;
    private LocalDateTime createdAt;
}
