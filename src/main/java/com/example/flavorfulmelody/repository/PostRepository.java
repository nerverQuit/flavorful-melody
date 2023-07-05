package com.example.flavorfulmelody.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.flavorfulmelody.entity.Post;

public interface PostRepository extends JpaRepository<Post,Long> {
	List<Post> findAllByOrderByCreatedAtDesc();

}