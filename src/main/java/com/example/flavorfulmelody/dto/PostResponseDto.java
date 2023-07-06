package com.example.flavorfulmelody.dto;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import com.example.flavorfulmelody.entity.Post;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponseDto {

	private Long id;
	private String content;
	private LocalDateTime createAt;
	private LocalDateTime modifiedAt;
	private List<CommentResponseDto> comments;

	public PostResponseDto(Post post){
		this.id = post.getId();
		this.content = post.getContent();
		this.createAt = post.getCreatedAt();
		this.modifiedAt = post.getModifiedAt();
		this.comments = post.getComments().stream()
				.map(CommentResponseDto::new)
				.sorted(Comparator.comparing(CommentResponseDto::getCreatedAt).reversed())
				.toList();
	}
}
