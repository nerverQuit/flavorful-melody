package com.example.flavorfulmelody.entity;

import com.example.flavorfulmelody.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="post")
@NoArgsConstructor
public class Post extends Timestamped {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 500)
	private String content;

	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	@OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
	private List<Comment> comments = new ArrayList<>();

	@Column
	private Long likeCnt = 0L;

	@Column
	private Long hateCnt = 0L;

	public Post(PostRequestDto requestDto){
		this.content = requestDto.getContent();
	}

	public void update(PostRequestDto requestDto){
		this.content = requestDto.getContent();
	}


}
