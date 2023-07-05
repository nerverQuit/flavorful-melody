package com.example.flavorfulmelody.controller;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;

import com.example.flavorfulmelody.dto.ApiResponseDto;
import com.example.flavorfulmelody.dto.PostListResponseDto;
import com.example.flavorfulmelody.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
	public ResponseEntity<PostListResponseDto> getPostList() {
		PostListResponseDto post_list = postService.getPostList();

		return ResponseEntity.ok().body(post_list);
	}

	// id 로 선택된 게시물 조회
	@GetMapping("/posts/{id}")
	public ResponseEntity<PostResponseDto> getPost(@PathVariable Long id){
		PostResponseDto post = postService.getPost(id);

		return ResponseEntity.ok().body(post);
	}

	// 게시글 작성
	@PostMapping("/posts")
	public ResponseEntity<PostResponseDto> createPost(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostRequestDto requestDto){
		PostResponseDto post = postService.createPost(requestDto, userDetails.getUser());

		return ResponseEntity.ok().body(post);
	}

	// 선택한 게시글 수정(변경)
	@PutMapping("/posts/{id}")
	public ResponseEntity<ApiResponseDto> updatePost (@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id, @RequestBody PostRequestDto requestDto) {
		try{
			postService.updatePost(id, requestDto, userDetails.getUser());
			return ResponseEntity.ok().body(new ApiResponseDto("게시글이 수정 되었습니다.", HttpStatus.OK.value()));
		} catch (RejectedExecutionException e){
			return ResponseEntity.badRequest().body(new ApiResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
		}

	}

	@DeleteMapping("/posts/{id}")
	public ResponseEntity<ApiResponseDto> deletePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long id) {
		try{
			postService.deletePost(id, userDetails.getUser());
			return ResponseEntity.ok().body(new ApiResponseDto("게시글이 삭제 되었습니다.", HttpStatus.OK.value()));
		} catch (RejectedExecutionException e){
			return ResponseEntity.badRequest().body(new ApiResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
		}
	}

}
