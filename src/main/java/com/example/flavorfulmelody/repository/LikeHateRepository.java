package com.example.flavorfulmelody.repository;

import com.example.flavorfulmelody.entity.LikeHate;
import com.example.flavorfulmelody.entity.LikeHateId;
import com.example.flavorfulmelody.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeHateRepository extends JpaRepository<LikeHate, LikeHateId> {
    LikeHate findByPostAndUser(Long postId, User user);
}
