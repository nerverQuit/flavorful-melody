package com.example.flavorfulmelody.dto;

import com.example.flavorfulmelody.entity.UserRoleEnum;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    @Pattern(regexp = "^[a-z0-9]{4,10}$",
            message = "최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9) 로 구성되어야 합니다.")
    private String username;

    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$",
            message = "최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9) 로 구성되어야 합니다.")
    private String password;

    @Pattern(regexp = "^[a-zA-Z0-9\uAC00-\uD7A3]{4,10}$",
            message = "최소 8자 이상, 10자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 문자로 구성되어야 합니다.")
    private String nickname;

    @Pattern(regexp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$",
            message = "최소 8자 이상, 10자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9), 문자로 구성되어야 합니다.")
    private String email;

    private UserRoleEnum role; // 회원 권한 (ADMIN, USER)
}
