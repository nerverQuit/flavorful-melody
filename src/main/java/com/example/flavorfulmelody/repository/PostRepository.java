package com.example.flavorfulmelody.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.flavorfulmelody.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
	List<Post> findAllByOrderByCreatedAtDesc();

}