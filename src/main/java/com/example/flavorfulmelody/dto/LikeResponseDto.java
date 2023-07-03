package com.example.flavorfulmelody.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikeResponseDto {
    private Long postId;
    private Long userId;
    private int upCount;
    private boolean isUp;

    public LikeResponseDto(Long postId, Long userId) {
        this.postId = postId;
        this.userId = userId;
        this.upCount = 0;
        this.isUp = false;
    }

    public void increaseUpCount() {
        if (isUp) {
            decreaseUpCount();
        } else {
            isUp = true;
            upCount++;
        }
    }

    public void decreaseUpCount() {
        if (isUp) {
            isUp = false;
            upCount--;
        }
    }
}
