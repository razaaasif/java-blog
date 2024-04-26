package com.raza.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raza.blog.dto.LoginRequest;
import com.raza.blog.dto.RegisterRequest;
import com.raza.blog.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/signup")
	public ResponseEntity<Object> signUp(@RequestBody RegisterRequest registerRequest) {
		this.authService.signUp(registerRequest);
		return new ResponseEntity<Object>(null, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public String login(@RequestBody LoginRequest loginRequest) {
		return this.authService.login(loginRequest);
	}

}
