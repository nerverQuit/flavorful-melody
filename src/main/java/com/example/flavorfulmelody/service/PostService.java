package com.example.flavorfulmelody.service;

import java.util.List;

import com.example.flavorfulmelody.dto.ApiResponseDto;
import com.example.flavorfulmelody.dto.PostListResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.flavorfulmelody.dto.PostRequestDto;
import com.example.flavorfulmelody.dto.PostResponseDto;
import com.example.flavorfulmelody.entity.Post;
import com.example.flavorfulmelody.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	private final PostRepository postRepository;

	public PostListResponseDto getPostList() {
		List<PostResponseDto> postList = postRepository.findAllByOrderByCreatedAtDesc().stream().map(PostResponseDto::new).toList();

		return new PostListResponseDto(postList);

	}

	public PostResponseDto getPost(Long id) {
		Post post = findPost(id);
		return new PostResponseDto(post);
	}

	public PostResponseDto createPost(PostRequestDto requestDto) {
		// RequestDto -> Entity
		Post post = new Post(requestDto);

		// DB 저장
		Post savePost = postRepository.save(post);
		return new PostResponseDto(savePost);
	}

	@Transactional
	public PostResponseDto updatePost(Long id, PostRequestDto requestDto) {
		// 해당 메모가 DB에 존재하는지 확인
		Post post = findPost(id);
		post.update(requestDto);
		return new PostResponseDto(post);
	}

	public void deletePost(Long id) {
		// 해당 메모가 DB에 존재하는지 확인
		Post post = findPost(id);

		postRepository.deleteById(id);
	}

	private Post findPost(Long id) {
		return postRepository.findById(id).orElseThrow(() ->
			new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
		); // 메모가 없다면 throw
	}
}
