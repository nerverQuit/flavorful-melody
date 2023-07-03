package com.example.flavorfulmelody.service;

import com.example.flavorfulmelody.dto.HateRequestDto;
import com.example.flavorfulmelody.dto.LikeRequestDto;
import com.example.flavorfulmelody.entity.Hate;
import com.example.flavorfulmelody.entity.Like;
import com.example.flavorfulmelody.repository.HateRepository;
import com.example.flavorfulmelody.repository.LikeRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class LikeHateService {
    private final LikeRepository likeRepository;
    private final HateRepository hateRepository;

    private final PostService postService;

    public LikeHateService(LikeRepository likeRepository, HateRepository hateRepository, PostService postService) {
        this.likeRepository = likeRepository;
        this.hateRepository = hateRepository;
        this.postService = postService;
    }

    private void getLikeUp(Model model, LikeRequestDto requestDto, User user) {
        Post post = postService.findPost(requestDto.getPostId());
        Like like = new Like(requestDto.getUpCount(), user, post);
        like.setPost(post);
        like.setUser(user);

        likeRepository.save(like);
    }

    private void getHateUp(Model model, HateRequestDto requestDto, User user) {
        Post post = postService.findPost(requestDto.getPostId());
        Hate hate = new Hate(requestDto.getUpCount(), user, post);
        hate.setPost(post);
        hate.setUser(user);

        hateRepository.save(hate);
    }
}
