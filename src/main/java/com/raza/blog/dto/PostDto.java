package com.raza.blog.dto;

import lombok.Data;

import java.util.Date;

@Data
public class PostDto {
	private Long id;
	private String content;
	private String title;
	private String username;
	private Date lastUpdated;
}
