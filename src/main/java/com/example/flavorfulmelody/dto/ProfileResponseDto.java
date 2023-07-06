package com.example.flavorfulmelody.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileResponseDto {
    private String eamil;
    private String nickname;

    public ProfileResponseDto(String email, String nickname) {
        this.eamil = email;
        this.nickname = nickname;
    }
}
