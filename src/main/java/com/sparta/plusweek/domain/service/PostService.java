package com.sparta.plusweek.domain.service;

import com.sparta.plusweek.domain.dto.*;
import com.sparta.plusweek.domain.dto.mapper.PostMapper;
import com.sparta.plusweek.domain.entity.Post;
import com.sparta.plusweek.domain.repository.PostRepository;
import com.sparta.plusweek.global.error.exception.InvalidPasswordException;
import com.sparta.plusweek.global.error.exception.PostNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 게시글 서비스
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final PasswordEncoder passwordEncoder;

    // 레포지토리로부터 생성일자를 기준으로 내림차순 리스트를 받아와 간소화 게시글 DTO 리스트로 변환 후 리턴
    public List<PostPreviewResponseDto> getPosts() {
        return postRepository.findAllByOrderByCreatedAtDesc()
                .stream()
                .map(postMapper::toPostPreviewResponseDto)
                .toList();
    }

    // 레포지토리로부터 특정 id의 게시글 세부 정보를 반환
    // @param id 게시글 id
    public PostDetailResponseDto getPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(PostNotFoundException::new);

        return postMapper.toPostDetailResponseDto(post);
    }

    // 게시글 생성 DTO 에서 비밀번호를 BCrypt 알고리즘으로 암호화 후 레포지토리에 저장함
    @Transactional
    public PostCreateResponseDto createPost(PostCreateRequestDto request) {
        passwordEncoding(request);

        Post createdPost = Post.create(request); // Post 엔티티의 자체 메서드를 이용해서 생성
        Post savedPost = postRepository.save(createdPost);

        return new PostCreateResponseDto(savedPost.getId());
    }

    private void passwordEncoding(PostCreateRequestDto request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        request.changeEncodedPassword(encodedPassword);
    }

    // 비밀번호를 검증 후 게시글을 수정함
    @Transactional
    public PostDetailResponseDto editPost(Long postId, PostEditRequestDto request, String password) {
        Post post = getValidatedPost(postId, password);
        post.edit(request); // Post 엔티티의 자체 수정 메서드를 이용해서 수정

        return postMapper.toPostDetailResponseDto(post);
    }

    // 비밀번호를 검증 후 게시글을 삭제함
    @Transactional
    public void delete(Long postId, String password) {
        Post post = getValidatedPost(postId, password);

        postRepository.delete(post);
    }

    private Post getValidatedPost(Long postId, String password) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        validatePassword(post, password);

        return post;
    }

    private void validatePassword(Post post, String password) {
        if (!passwordEncoder.matches(password, post.getPassword())) {
            throw new InvalidPasswordException();
        }
    }
}
