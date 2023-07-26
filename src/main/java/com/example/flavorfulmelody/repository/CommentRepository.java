package com.example.flavorfulmelody.repository;

import com.example.flavorfulmelody.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
