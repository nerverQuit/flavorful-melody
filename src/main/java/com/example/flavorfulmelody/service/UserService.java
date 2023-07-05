package com.example.flavorfulmelody.service;

import com.example.flavorfulmelody.dto.UserRequestDto;
import com.example.flavorfulmelody.entity.User;
import com.example.flavorfulmelody.entity.UserRoleEnum;
import com.example.flavorfulmelody.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    public void signup(UserRequestDto requestDto) {
        String userid = requestDto.getUserid();
        String password = passwordEncoder.encode(requestDto.getPassword());
        String nickname = requestDto.getNickname();
        String email = requestDto.getEmail();
        UserRoleEnum role = requestDto.getRole();

        //ID 중복체크, 닉네임 중복체크
        if (userRepository.findByUserid(userid).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 ID 입니다.");
        } else if (userRepository.findByNickname(nickname).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 닉네임 입니다.");
        } else if (userRepository.findByEmail(email).isPresent()) {
            throw  new IllegalArgumentException("이미 존재하는 이메일 입니다.");
        }

        //중복되지 않은 ID, 닉네임 -> 데이터베이스에 유저 정보 저장(회원가입)
        User user = new User(userid, password, nickname, email, role);
        userRepository.save(user);
    }

    //로그인
    public void login(UserRequestDto loginRequestDto) {
        String userid = loginRequestDto.getUserid();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByUserid(userid).orElseThrow(
                () -> new IllegalArgumentException("등록되지 않은 회원입니다.")
        );

        if(passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
