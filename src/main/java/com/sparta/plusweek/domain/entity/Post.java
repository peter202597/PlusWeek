package com.sparta.plusweek.domain.entity;

import com.sparta.plusweek.domain.dto.PostCreateRequestDto;
import com.sparta.plusweek.domain.dto.PostEditRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 게시글 엔티티.
@Getter
@Entity
@Table(name = "post") // 테이블명 = post
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA는 기본 생성자를 필요하므로 최소의 접근제어자로 생성
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500) // 제목은 최대 500
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false,length = 5000) // 내용은 최대 5000
    private String contents;

    private Post(String title, String author, String password, String contents) { // private 로 선언
        this.title = title;
        this.author = author;
        this.password = password;
        this.contents = contents;
    }

    public static Post create(PostCreateRequestDto request) { // 특정 DTO로 생성을 제한함
        return new Post(request.getTitle(), request.getAuthor(), request.getPassword(), request.getContents());
    }

    public void edit(PostEditRequestDto request) { // 특정 DTO로 수정을 제한함
        this.title = request.getTitle();
        this.author = request.getAuthor();
        this.contents = request.getContents();
    }
}
