package com.example.flavorfulmelody.service;

import com.example.flavorfulmelody.dto.ProfileRequestDto;
import com.example.flavorfulmelody.dto.ProfileResponseDto;
import com.example.flavorfulmelody.entity.User;
import com.example.flavorfulmelody.entity.UserRoleEnum;
import com.example.flavorfulmelody.repository.UserRepository;
import com.example.flavorfulmelody.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfileResponseDto getProfile(User user) {
        String email = user.getEmail();
        String nickname = user.getNickname();

        return new ProfileResponseDto(email, nickname);
    }

    @Transactional
    public void updateProfile(ProfileRequestDto requestDto, User user) {
        String checkPassword = requestDto.getCheckPassword();
        String nickname = requestDto.getNickname();

        User persistence = userRepository.findByUsername(user.getUsername()).orElseThrow(() -> {
                return new RejectedExecutionException("회원 찾기 실패");
        });

        if (!passwordEncoder.matches(checkPassword, user.getPassword())){
            throw new RejectedExecutionException("비밀번호가 일치하지 않습니다.");
        }

        if (userRepository.findByNickname(nickname).isPresent()){
            throw new RejectedExecutionException("중복되는 닉네임 입니다. 다시 입력해주세요");
        }

        persistence.setPassword(passwordEncoder.encode(requestDto.getChangePassword()));
        persistence.setNickname(requestDto.getNickname());
    }

    public boolean checkPassword(String iPassword, User user){
        String password = user.getPassword();

        if(!passwordEncoder.matches(iPassword, password)){ // matches -> 사용자가 입력한 password 를 자동으로 인코딩해서 db의 인코딩된 password와 비교 !
            throw new IllegalArgumentException();
        }
        return true;
    }
}
