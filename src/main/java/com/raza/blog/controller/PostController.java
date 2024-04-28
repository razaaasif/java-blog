package com.raza.blog.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raza.blog.dto.PostDto;
import com.raza.blog.exception.PostNotFoundException;
import com.raza.blog.service.PostService;

@RestController
@RequestMapping("/api/posts")
public class PostController {
	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@PostMapping
	public ResponseEntity<PostDto> savePost(@RequestBody PostDto postDto) {
		logger.debug("savePost() " + postDto);
		postDto = this.postService.savePost(postDto);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<PostDto>> getPosts() {
		return new ResponseEntity<List<PostDto>>(this.postService.getAllPost(), HttpStatus.OK);
	}

	@GetMapping("{id}")
	public ResponseEntity<PostDto> getPosts(@PathVariable("id") Long id) {
		try {
			return new ResponseEntity<PostDto>(this.postService.getPostById(id), HttpStatus.FOUND);
		} catch (PostNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
