package com.example.flavorfulmelody.entity;

import com.example.flavorfulmelody.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="post")
@NoArgsConstructor
public class Post extends Timestamped {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "content", nullable = false, length = 500)
	private String content;

	// @Column(name = "likeCnt", nullable = false)
	// private Long likeCnt;
	//
	// @Column(name = "hateCnt", nullable = false)
	// private Long hateCnt;

	public Post(PostRequestDto requestDto){
		this.content = requestDto.getContent();
		// this.likeCnt = requestDto.getLikeCnt();
		// this.unlikeCnt = requestDto.getHateCnt();
	}

	public void update(PostRequestDto requestDto){
		// this.user_id = requestDto.getuser();
		this.content = requestDto.getContent();
		// this.likeCnt = requestDto.getLikeCnt();
		// this.unlikeCnt = requestDto.getHateCnt();
	}


}
