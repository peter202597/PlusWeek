package com.sparta.plusweek.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// 댓글 작성 요청 Dto
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateRequestDto {

    private String content;
}
