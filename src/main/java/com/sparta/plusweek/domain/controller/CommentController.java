package com.sparta.plusweek.domain.controller;

import com.sparta.plusweek.domain.dto.CommentCreateRequestDto;
import com.sparta.plusweek.domain.dto.CommentEditRequestDto;
import com.sparta.plusweek.domain.dto.CommentResponseDto;
import com.sparta.plusweek.domain.dto.CustomUserDetails;
import com.sparta.plusweek.domain.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

// 댓글 컨트롤러
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    // @param todoCardId 어느 게시물에 달린 댓글인지 체크
    @PostMapping("/post/{postId}")
    public ResponseEntity<CommentResponseDto> createComment(
            @PathVariable Long postId,
            @RequestBody CommentCreateRequestDto requestDto,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        CommentResponseDto responseDto = commentService.createComment(postId, requestDto, userDetails.getUser());

        return ResponseEntity.ok(responseDto);
    }

    // 댓글 수정
    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> editComment(
            @PathVariable Long commentId,
            @RequestBody CommentEditRequestDto requestDto,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        CommentResponseDto responseDto = commentService.editComment(commentId, requestDto, userDetails.getUser());

        return ResponseEntity.ok(responseDto);
    }

    /// 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        commentService.deleteComment(commentId, userDetails.getUser());

        return ResponseEntity.ok().build();
    }
}
