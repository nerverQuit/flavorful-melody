package com.example.flavorfulmelody.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// @Entity
@Getter
@Setter
@NoArgsConstructor
public class LikeHate {

    private boolean isLike;
    private boolean isHate;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public LikeHate(Post post, User user) {
        this.isLike = false;
        this.isHate = false;
        this.post = post;
        this.user = user;
    }
}
