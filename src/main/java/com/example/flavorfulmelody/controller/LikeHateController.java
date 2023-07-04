package com.example.flavorfulmelody.controller;

import com.example.flavorfulmelody.dto.ApiResponseDto;
import com.example.flavorfulmelody.entity.User;
import com.example.flavorfulmelody.service.LikeHateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.RejectedExecutionException;

@RestController
@RequestMapping("/api")
public class LikeHateController {

    private final LikeHateService likeHateService;

    public LikeHateController(LikeHateService likeHateService) {
        this.likeHateService = likeHateService;
    }

    @PutMapping("/posts/{postId}/like")
    public ResponseEntity<ApiResponseDto> toggleLikeOnPost(@PathVariable Long postId, User user) {
        try {
            boolean isLiked = likeHateService.checkIfLiked(postId, user);

            if (isLiked) {
                likeHateService.removeLikeFromPost(postId, user);
                return ResponseEntity.ok().body(new ApiResponseDto("좋아요 취소", HttpStatus.OK.value()));
            } else {
                likeHateService.addLikeToPost(postId, user);
                return ResponseEntity.ok().body(new ApiResponseDto("좋아요", HttpStatus.OK.value()));
            }
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("유효하지 않은 토큰입니다", HttpStatus.BAD_REQUEST.value()));
        }
    }


    @PutMapping("/posts/{postId}/hate")
    public ResponseEntity<ApiResponseDto> toggleHateOnPost(@PathVariable Long postId, User user) {
        try {
            boolean isHated = likeHateService.checkIfHated(postId, user);

            if (isHated) {
                likeHateService.removeHateFromPost(postId, user);
                return ResponseEntity.ok().body(new ApiResponseDto("싫어요 취소", HttpStatus.OK.value()));
            } else {
                likeHateService.addHateToPost(postId, user);
                return ResponseEntity.ok().body(new ApiResponseDto("싫어요", HttpStatus.OK.value()));
            }
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("유효하지 않은 토큰입니다", HttpStatus.BAD_REQUEST.value()));
        }
    }

}
