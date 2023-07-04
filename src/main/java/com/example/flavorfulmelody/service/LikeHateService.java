package com.example.flavorfulmelody.service;

import com.example.flavorfulmelody.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface LikeHateService {
    boolean checkIfLiked(Long postId, User user);
    boolean checkIfHated(Long postId, User user);
    void addLikeToPost(Long postId, User user);
    void addHateToPost(Long postId, User user);
    void removeLikeFromPost(Long postId, User user);
    void removeHateFromPost(Long postId, User user);
}
