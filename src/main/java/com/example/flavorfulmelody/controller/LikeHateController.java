package com.example.flavorfulmelody.controller;

import com.example.flavorfulmelody.dto.ApiResponseDto;
import com.example.flavorfulmelody.service.LikeHateServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.RejectedExecutionException;

@RestController
@RequestMapping("/api")
public class LikeHateController {

    private final LikeHateServiceImpl likeHateServiceImpl;

    public LikeHateController(LikeHateServiceImpl likeHateServiceImpl) {
        this.likeHateServiceImpl = likeHateServiceImpl;
    }

    @PostMapping("/posts/{id}/like")
    public ResponseEntity<ApiResponseDto> toggleLikeOnPost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            boolean isLiked = likeHateServiceImpl.checkIfLiked(id, userDetails.getUser());

            if (isLiked) {
                likeHateServiceImpl.removeLikeFromPost(id, userDetails.getUser());
                return ResponseEntity.ok().body(new ApiResponseDto("좋아요 취소", HttpStatus.OK.value()));
            } else {
                likeHateServiceImpl.addLikeToPost(id, userDetails.getUser());
                return ResponseEntity.ok().body(new ApiResponseDto("좋아요", HttpStatus.OK.value()));
            }
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("유효하지 않은 토큰입니다", HttpStatus.BAD_REQUEST.value()));
        }
    }


    @PostMapping("/posts/{id}/hate")
    public ResponseEntity<ApiResponseDto> toggleHateOnPost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        try {
            boolean isHated = likeHateServiceImpl.checkIfHated(id, userDetails.getUser());

            if (isHated) {
                likeHateServiceImpl.removeHateFromPost(id, userDetails.getUser());
                return ResponseEntity.ok().body(new ApiResponseDto("싫어요 취소", HttpStatus.OK.value()));
            } else {
                likeHateServiceImpl.addHateToPost(id, userDetails.getUser());
                return ResponseEntity.ok().body(new ApiResponseDto("싫어요", HttpStatus.OK.value()));
            }
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto("유효하지 않은 토큰입니다", HttpStatus.BAD_REQUEST.value()));
        }
    }

}
