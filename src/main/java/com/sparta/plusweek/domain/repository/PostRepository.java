package com.sparta.plusweek.domain.repository;

import com.sparta.plusweek.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByCreatedAtDesc(); // 생성일자를 기준으로 내림차순한 게시글 리스트를 반환
}
