package com.raza.blog.service;

import java.util.List;

import com.raza.blog.dto.PostDto;

public interface PostService {
	public PostDto savePost(PostDto postDto);
	public List<PostDto> getAllPost();
	public PostDto getPostById(Long id);
}
