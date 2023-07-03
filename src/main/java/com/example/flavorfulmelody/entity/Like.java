package com.example.flavorfulmelody.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int upCount;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Like(int upCount, User user, Post post) {
        this.upCount = upCount;
        this.user = user;
        this.post = post;
    }
}
