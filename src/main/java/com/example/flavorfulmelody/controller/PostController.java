package com.example.flavorfulmelody.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.flavorfulmelody.dto.PostRequestDto;
import com.example.flavorfulmelody.dto.PostResponseDto;
import com.example.flavorfulmelody.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
	private final PostService postService;

	// 전체 피드 조회
	@GetMapping("/posts")
	public List<PostResponseDto> getPostList() {
		return postService.getPostList();
	}

	// id 로 선택된 게시물 조회
	@GetMapping("/posts/{id}")
	public PostResponseDto getPost(@PathVariable Long id){
		return postService.getPost(id);
	}

	// 게시글 작성
	@PostMapping("/posts")
	public PostResponseDto createPost(@RequestBody PostRequestDto requestDto){
		return postService.createPost(requestDto);
	}

	// 선택한 게시글 수정(변경)
	@PutMapping("/posts/{id}")
	public PostResponseDto updatePost (@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
		return postService.updatePost(id, requestDto);
	}

	@DeleteMapping("/posts/{id}")
	public PostResponseDto deletePost(@PathVariable Long id) {
		return postService.deletePost(id);
	}

}
