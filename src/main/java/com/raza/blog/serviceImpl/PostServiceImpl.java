package com.raza.blog.serviceImpl;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.raza.blog.config.JwtUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.raza.blog.dto.PostDto;
import com.raza.blog.entity.Post;
import com.raza.blog.exception.PostNotFoundException;
import com.raza.blog.repository.interfaces.PostRepository;
import com.raza.blog.service.PostService;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

	private final PostRepository postRepository;
	private final JwtUtils jwtUtils;

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
		postDto.setLastUpdated(savedPost.getUpdatedOn());
		return postDto;
	}

	private Post createPost(PostDto postDto) {
		Post post = new Post();
		post.setContent(postDto.getContent());
		post.setTitle(postDto.getTitle());
		post.setCreatedOn(new Date());
		UserDetails userDetails = this.getCurrentLoggedUser();
		log.debug("createPost() {}" , userDetails);
		post.setUsername(userDetails.getUsername());

		return post;
	}

	private UserDetails getCurrentLoggedUser(){
		return  (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	}
}
