package com.example.flavorfulmelody.dto;

import java.time.LocalDateTime;

import com.example.flavorfulmelody.entity.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto extends ApiResponseDto{
    private String comment;
    private String username;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(Comment comment) {
        super();
        this.comment = comment.getComment();
        this.username = comment.getUser().getUsername();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }
}
