package com.raza.blog.service.Impl;

import java.time.Instant;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.raza.blog.dto.PostDto;
import com.raza.blog.entity.Post;
import com.raza.blog.repository.PostRepository;
import com.raza.blog.service.AuthService;
import com.raza.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;
	private final AuthService authService;

	public PostServiceImpl(PostRepository postRepository, AuthService authService) {
		this.postRepository = postRepository;
		this.authService = authService;
	}

	@Override
	public PostDto savePost(PostDto postDto) {
		Post post = this.createPost(postDto);
		Post savedPost = this.postRepository.save(post);
		return this.createPostDto(savedPost);
	}

	private PostDto createPostDto(Post savedPost) {
		PostDto postDto = new PostDto();
		postDto.setTitle(savedPost.getTitle());
		postDto.setContent(savedPost.getContent());
		postDto.setId(savedPost.getId());
		postDto.setUsername(savedPost.getUsername());
		return postDto;
	}

	private Post createPost(PostDto postDto) {
		Post post = new Post();
		post.setContent(postDto.getContent());
		post.setTitle(postDto.getTitle());
		User user = this.authService.getCurrentUser()
				.orElseThrow(() -> new IllegalArgumentException("No user logged in"));
		System.out.println(user);
		post.setUsername(user.getUsername());
		post.setCreatedOn(Instant.now());

		return post;
	}

}
