package com.raza.blog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raza.blog.dto.PostDto;
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
		System.out.println("savePost() " + postDto);
		postDto = this.postService.savePost(postDto);
		return new ResponseEntity<PostDto>(postDto, HttpStatus.CREATED);
	}

}
