package com.example.flavorfulmelody.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private Long post_id;
    private String body;
}
