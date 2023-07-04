package com.example.flavorfulmelody.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String contents;

    @Column
    private int likeCnt;

    @Column
    private int hateCnt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
