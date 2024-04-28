package com.raza.blog.service;

import java.util.Optional;

import com.raza.blog.dto.LoginRequest;
import com.raza.blog.dto.RegisterRequest;

public interface AuthService {
	public void signUp(RegisterRequest registerRequest);

	public String login(LoginRequest loginRequest);

	public Optional<org.springframework.security.core.userdetails.User> getCurrentUser();
}
