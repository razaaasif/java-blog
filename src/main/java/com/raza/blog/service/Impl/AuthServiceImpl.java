package com.raza.blog.service.Impl;

import org.springframework.stereotype.Service;

import com.raza.blog.dto.RegisterRequest;
import com.raza.blog.entity.User;
import com.raza.blog.repository.UserRepository;
import com.raza.blog.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

	private UserRepository userRepository;

	public AuthServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void signUp(RegisterRequest registerRequest) {
		User user = new User();
		user.setUsername(registerRequest.getUsername());
		user.setPassword(registerRequest.getPassword());
		user.setEmail(registerRequest.getEmail());
		this.userRepository.save(user);

	}

}
