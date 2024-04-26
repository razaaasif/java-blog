package com.raza.blog.service;

import com.raza.blog.dto.LoginRequest;
import com.raza.blog.dto.RegisterRequest;

public interface AuthService {
	public void signUp(RegisterRequest registerRequest);

	public String login(LoginRequest loginRequest);
}
