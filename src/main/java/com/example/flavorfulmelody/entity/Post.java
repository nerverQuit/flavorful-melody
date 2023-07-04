package com.example.flavorfulmelody.entity;

import com.example.flavorfulmelody.dto.PostRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="posts")
@NoArgsConstructor
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long post_id;

	@Column(name = "user_id", nullable = false)
	private String user_id;

	@Column(name = "content", nullable = false, length = 500)
	private String content;

	@Column(name = "likeCnt", nullable = false)
	private Long likeCnt;

	@Column(name = "unikeCnt", nullable = false)
	private Long unlikeCnt;

	public Post(PostRequestDto requestDto){
		this.content = requestDto.getContents();
		this.likeCnt = requestDto.getLikeCnt();
		this.unlikeCnt = requestDto.getUnlikeCnt();
	}

	public void update(PostRequestDto requestDto){
		this.content = requestDto.getContents();
		this.likeCnt = requestDto.getLikeCnt();
		this.unlikeCnt = requestDto.getUnlikeCnt();
	}


}
