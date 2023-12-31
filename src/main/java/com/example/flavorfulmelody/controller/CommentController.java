package com.example.flavorfulmelody.controller;

import java.util.concurrent.RejectedExecutionException;

import com.example.flavorfulmelody.dto.ApiResponseDto;
import com.example.flavorfulmelody.security.UserDetailsImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.flavorfulmelody.dto.CommentRequestDto;
import com.example.flavorfulmelody.service.CommentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<ApiResponseDto> createComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CommentRequestDto requestDto) {
        commentService.createComment(requestDto, userDetails.getUser());

        return ResponseEntity.ok().body(new ApiResponseDto("댓글이 작성 되었습니다", HttpStatus.CREATED.value()));
    }

    @PutMapping("/comments/{comment_id}")
    public ResponseEntity<ApiResponseDto> updateComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long comment_id, @RequestBody CommentRequestDto requestDto) {
        try {
            commentService.updateComment(comment_id, requestDto, userDetails.getUser());
            return ResponseEntity.ok().body(new ApiResponseDto("댓글 수정이 완료 되었습니다",HttpStatus.OK.value()));
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("작성자만 수정 할 수 있습니다,",HttpStatus.BAD_REQUEST.value()));
        }
    }

    @DeleteMapping("/comments/{comment_id}")
    public ResponseEntity<ApiResponseDto> deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long comment_id) {
        try {
            commentService.deleteComment(comment_id, userDetails.getUser());
            return ResponseEntity.ok().body(new ApiResponseDto("댓글 삭제가 완료 되었습니다", HttpStatus.OK.value()));
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("작성자만 삭제 할 수 있습니다",HttpStatus.BAD_REQUEST.value()));
        }
    }
}
