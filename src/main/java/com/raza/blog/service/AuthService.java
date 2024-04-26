package com.raza.blog.service;

import com.raza.blog.dto.RegisterRequest;

public interface AuthService {
	public void signUp(RegisterRequest registerRequest);
}
