package com.example.flavorfulmelody.service;

import java.util.concurrent.RejectedExecutionException;

import com.example.flavorfulmelody.entity.Post;
import com.example.flavorfulmelody.entity.User;
import com.example.flavorfulmelody.entity.UserRoleEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.flavorfulmelody.dto.CommentRequestDto;
import com.example.flavorfulmelody.dto.CommentResponseDto;
import com.example.flavorfulmelody.entity.Comment;
import com.example.flavorfulmelody.repository.CommentRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final PostService postService;
    private final CommentRepository commentRepository;

    public CommentResponseDto createComment(CommentRequestDto requestDto, User user) {
        Post post = postService.findPost(requestDto.getPost_id());
        Comment comment = new Comment(requestDto.getComment());
        comment.setUser(user);
        comment.setPost(post);

        commentRepository.save(comment);

        return  new CommentResponseDto(comment);
    }

    public void deleteComment(Long comment_id, User user) {
        Comment comment = commentRepository.findById(comment_id).orElseThrow();

        // 요청자가 운영자 이거나 댓글 작성자(post.user) 와 요청자(user) 가 같은지 체크
        if (!user.getRole().equals(UserRoleEnum.ADMIN) && !comment.getUser().equals(user)) {
            throw new RejectedExecutionException();
        }

        commentRepository.delete(comment);
    }

    @Transactional
    public CommentResponseDto updateComment(Long comment_id, CommentRequestDto requestDto, User user) {
        Comment comment = commentRepository.findById(comment_id).orElseThrow();

        // 요청자가 운영자 이거나 댓글 작성자(post.user) 와 요청자(user) 가 같은지 체크
        if (!user.getRole().equals(UserRoleEnum.ADMIN) && !comment.getUser().equals(user)) {
            throw new RejectedExecutionException();
        }

        comment.setComment(requestDto.getComment());

        return new CommentResponseDto(comment);
    }
}
