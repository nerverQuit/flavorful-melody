package com.example.flavorfulmelody.service;

import java.util.List;
import java.util.concurrent.RejectedExecutionException;

import com.example.flavorfulmelody.dto.ApiResponseDto;
import com.example.flavorfulmelody.dto.PostListResponseDto;
import com.example.flavorfulmelody.entity.User;
import com.example.flavorfulmelody.entity.UserRoleEnum;
import com.example.flavorfulmelody.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public PostListResponseDto getPostList() {
		List<PostResponseDto> postList = postRepository.findAllByOrderByCreatedAtDesc().stream().map(PostResponseDto::new).toList();

		return new PostListResponseDto(postList);

	}

	public PostResponseDto getPost(Long id) {
		Post post = findPost(id);
		return new PostResponseDto(post);
	}

	public PostResponseDto createPost(PostRequestDto requestDto, User user) {
		// RequestDto -> Entity
		Post post = new Post(requestDto);
		post.setUser(user);

		// DB 저장
		Post savePost = postRepository.save(post);
		return new PostResponseDto(savePost);
	}

	@Transactional
	public PostResponseDto updatePost(Long id, PostRequestDto requestDto, User user) {
		// 해당 메모가 DB에 존재하는지 확인
		Post post = findPost(id);

		//작성자 또는 관리자가 맞는지 확인
		if (!(user.getRole().equals(UserRoleEnum.ADMIN) || post.getUser().equals(user))) {
			throw new RejectedExecutionException("작성자가 아닙니다.");
		}
		post.setContent(requestDto.getContent());
		post.update(requestDto);
		return new PostResponseDto(post);
	}

	public void deletePost(Long id, User user) {
		// 해당 메모가 DB에 존재하는지 확인
		Post post = findPost(id);

		if (!(user.getRole().equals(UserRoleEnum.ADMIN) || post.getUser().equals(user))) {
			throw new RejectedExecutionException("작성자가 아닙니다.");
		}

		postRepository.delete(post);
	}

	private Post findPost(Long id) {
		return postRepository.findById(id).orElseThrow(() ->
			new IllegalArgumentException("해당 게시글이 존재하지 않습니다.")
		); // 메모가 없다면 throw
	}
}
