package com.sparta.plusweek.domain.entity;

import com.sparta.plusweek.domain.dto.CommentEditRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comments") // comment 는 예약어 이므로 s를 붙임
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 최소 범위 접근 제어자
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post Post;

    public Comment(String content, User author, Post post) {
        this.content = content;
        this.author = author;
        this.Post = post;
    }

    public void update(CommentEditRequestDto requestDto) {
        this.content = requestDto.getContent();
    }
}
