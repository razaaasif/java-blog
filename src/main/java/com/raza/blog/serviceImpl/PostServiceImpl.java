package com.raza.blog.serviceImpl;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.raza.blog.dto.PostDto;
import com.raza.blog.entity.Post;
import com.raza.blog.exception.PostNotFoundException;
import com.raza.blog.repository.interfaces.PostRepository;
import com.raza.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;

	public PostServiceImpl(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public PostDto savePost(PostDto postDto) {
		Post post = this.createPost(postDto);
		Post savedPost = this.postRepository.save(post);
		return this.createPostDto(savedPost);
	}

	@Override
	public List<PostDto> getAllPost() {
		return this.postRepository.findAll().stream().map(this::createPostDto).collect(Collectors.toList());
	}

	@Override
	public PostDto getPostById(Long id) {
		Post post = this.postRepository.findById(id)
				.orElseThrow(() -> new PostNotFoundException("No post found for ID : " + id));
		return this.createPostDto(post);
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
		post.setCreatedOn(Instant.now());

		return post;
	}
}
