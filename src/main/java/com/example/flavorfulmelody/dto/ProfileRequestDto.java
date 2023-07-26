package com.example.flavorfulmelody.dto;

import lombok.Getter;

@Getter
public class ProfileRequestDto {
    private String checkPassword;
    private String changePassword;
    private String nickname;
}
