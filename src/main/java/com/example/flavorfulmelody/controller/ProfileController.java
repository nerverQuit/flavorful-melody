package com.example.flavorfulmelody.controller;

import com.example.flavorfulmelody.dto.ApiResponseDto;
import com.example.flavorfulmelody.dto.ProfileRequestDto;
import com.example.flavorfulmelody.dto.ProfileResponseDto;
import com.example.flavorfulmelody.repository.UserRepository;
import com.example.flavorfulmelody.security.UserDetailsImpl;
import com.example.flavorfulmelody.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.RejectedExecutionException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProfileController {
    private final ProfileService profileService;

    //마이페이지 정보 불러오기
    @GetMapping("/mypage")
    public ResponseEntity<ProfileResponseDto> getProfile(@AuthenticationPrincipal UserDetailsImpl userDetails){
        ProfileResponseDto result = profileService.getProfile(userDetails.getUser());

        return ResponseEntity.ok().body(result);
    }

    //마이페이지에서 정보 수정하기 (닉네임, 비밀번호)
    @PutMapping("/mypage")
    public ResponseEntity<ApiResponseDto> updateProfile(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody ProfileRequestDto requestDto){
        try {
            profileService.updateProfile(requestDto, userDetails.getUser());
            return ResponseEntity.ok().body(new ApiResponseDto("수정이 완료되었습니다", HttpStatus.OK.value()));
        } catch (RejectedExecutionException e) {
            return ResponseEntity.badRequest().body(new ApiResponseDto(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }
}
