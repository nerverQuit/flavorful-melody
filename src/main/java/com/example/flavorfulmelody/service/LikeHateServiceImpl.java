package com.example.flavorfulmelody.service;

import com.example.flavorfulmelody.entity.LikeHate;
import com.example.flavorfulmelody.entity.Post;
import com.example.flavorfulmelody.entity.User;
import com.example.flavorfulmelody.repository.LikeHateRepository;
import com.example.flavorfulmelody.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeHateServiceImpl implements LikeHateService {

    private final LikeHateRepository likeHateRepository;
    private final PostRepository postRepository;

    public LikeHateServiceImpl(LikeHateRepository likeHateRepository, PostRepository postRepository, PostService postService) {
        this.likeHateRepository = likeHateRepository;
        this.postRepository = postRepository;
    }

    @Override
    public boolean checkIfLiked(Long postId, User user) {
        LikeHate likeHate = likeHateRepository.findByPostAndUser(postId, user);
        return likeHate != null && likeHate.isLike();
    }

    @Override
    public boolean checkIfHated(Long postId, User user) {
        LikeHate likeHate = likeHateRepository.findByPostAndUser(postId, user);
        return likeHate != null && likeHate.isHate();
    }

    @Override
    public void addLikeToPost(Long postId, User user) {
        LikeHate likeHate = likeHateRepository.findByPostAndUser(postId, user);
        if (likeHate == null) {
            Post post = postRepository.findById(postId).orElseThrow(
                    () -> new IllegalArgumentException("해당 글을 찾을 수 없습니다."));
            likeHate = new LikeHate(post, user);
        }
        likeHate.setLike(true);
        Post post = likeHate.getPost();
        post.setLikeCnt(post.getLikeCnt() + 1);
        likeHateRepository.save(likeHate);
    }

    @Override
    public void addHateToPost(Long postId, User user) {
        LikeHate likeHate = likeHateRepository.findByPostAndUser(postId, user);
        if (likeHate == null) {
            Post post = postRepository.findById(postId).orElseThrow(
                    () -> new IllegalArgumentException("해당 글을 찾을 수 없습니다."));
            likeHate = new LikeHate(post, user);
        }
        likeHate.setHate(true);
        Post post = likeHate.getPost();
        post.setHateCnt(post.getHateCnt() + 1);
        likeHateRepository.save(likeHate);
    }

    @Override
    public void removeLikeFromPost(Long postId, User user) {
        LikeHate likeHate = likeHateRepository.findByPostAndUser(postId, user);
        if(likeHate != null) {
            likeHate.setLike(false);

            Post post = likeHate.getPost();
            post.setLikeCnt(post.getLikeCnt() - 1);

            likeHateRepository.save(likeHate);
        }
    }

    @Override
    public void removeHateFromPost(Long postId, User user) {
        LikeHate likeHate = likeHateRepository.findByPostAndUser(postId, user);
        if (likeHate != null) {
            likeHate.setHate(false);

            Post post = likeHate.getPost();
            post.setHateCnt(post.getHateCnt() - 1);

            likeHateRepository.save(likeHate);
        }
    }
}
