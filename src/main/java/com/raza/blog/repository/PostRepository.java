package com.raza.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raza.blog.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
