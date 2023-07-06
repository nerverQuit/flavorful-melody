package com.example.flavorfulmelody.service;

import com.example.flavorfulmelody.entity.LikeHate;
import com.example.flavorfulmelody.entity.Post;
import com.example.flavorfulmelody.entity.User;
import com.example.flavorfulmelody.repository.LikeHateRepository;
import com.example.flavorfulmelody.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class LikeHateServiceImpl implements LikeHateService {

    private final LikeHateRepository likeHateRepository;
    private final PostRepository postRepository;

    public LikeHateServiceImpl(LikeHateRepository likeHateRepository, PostRepository postRepository) {
        this.likeHateRepository = likeHateRepository;
        this.postRepository = postRepository;
    }

    @Override
    public boolean checkIfLiked(Long postId, User user) {
        LikeHate likeHate = likeHateRepository.findByPostIdAndUser(postId, user);
        return likeHate != null && likeHate.isLike();
    }

    @Override
    public boolean checkIfHated(Long postId, User user) {
        LikeHate likeHate = likeHateRepository.findByPostIdAndUser(postId, user);
        return likeHate != null && likeHate.isHate();
    }

    @Override
    public void addLikeToPost(Long postId, User user) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null) {
            LikeHate likeHate = likeHateRepository.findByPostIdAndUser(postId, user);
            if (likeHate == null) {
                likeHate = new LikeHate();
                likeHate.setPost(post);
                likeHate.setUser(user);
            } else {
                likeHate.setLike(true);
            }
            post.setLikeCnt(post.getLikeCnt() + 1);
            likeHateRepository.save(likeHate);
        }
    }


    @Override
    public void addHateToPost(Long postId, User user) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null) {
            LikeHate likeHate = likeHateRepository.findByPostIdAndUser(postId, user);
            if (likeHate == null) {
                likeHate = new LikeHate();
                likeHate.setPost(post);
                likeHate.setUser(user);
            } else {
                likeHate.setHate(true);
            }
            post.setHateCnt(post.getHateCnt() + 1);
            likeHateRepository.save(likeHate);
        }
    }

    @Override
    public void removeLikeFromPost(Long postId, User user) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null) {
            LikeHate likeHate = likeHateRepository.findByPostIdAndUser(postId, user);
            if (likeHate == null) {
                likeHate = new LikeHate();
                likeHate.setPost(post);
                likeHate.setUser(user);
            } else {
                likeHate.setLike(false);
            }
            post.setLikeCnt(post.getLikeCnt() - 1);
            likeHateRepository.save(likeHate);
        }
    }

    @Override
    public void removeHateFromPost(Long postId, User user) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null) {
            LikeHate likeHate = likeHateRepository.findByPostIdAndUser(postId, user);
            if (likeHate == null) {
                likeHate = new LikeHate();
                likeHate.setPost(post);
                likeHate.setUser(user);
            } else {
                likeHate.setHate(false);
            }
            post.setHateCnt(post.getHateCnt() - 1);
            likeHateRepository.save(likeHate);
        }
    }
}

