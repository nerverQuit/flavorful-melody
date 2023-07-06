package com.example.flavorfulmelody.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@IdClass(LikeHateId.class)
public class LikeHate {

    @Column
    private boolean isLike;

    @Column
    private boolean isHate;

    @Id
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}

