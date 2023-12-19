package com.sparta.plusweek.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 댓글 수정 요청 Dto
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentEditRequestDto {

    private String content;
}
