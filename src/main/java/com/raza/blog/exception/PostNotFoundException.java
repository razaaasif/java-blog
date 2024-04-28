package com.raza.blog.exception;

public class PostNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -3340624237657563540L;

	public PostNotFoundException(String message) {
		super(message);
	}

}
