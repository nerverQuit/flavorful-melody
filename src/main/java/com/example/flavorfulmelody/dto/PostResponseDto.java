package com.example.flavorfulmelody.dto;

import java.time.LocalDateTime;

import com.example.flavorfulmelody.entity.Post;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponseDto {

	private Long id;
	private String content;
	private Long likeCnt;
	private Long hateCnt;
	private LocalDateTime createAt;
	private LocalDateTime modifiedAt;

	public PostResponseDto(Post post){
		this.id = post.getId();
		this.content = post.getContent();
		// this.likeCnt = post.getLikeCnt();
		// this.hateCnt = post.getHateCnt();
		this.createAt = post.getCreatedAt();
		this.modifiedAt = post.getModifiedAt();
	}
}
